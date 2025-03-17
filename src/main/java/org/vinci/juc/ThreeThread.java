package org.vinci.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeThread {

    static final ReentrantLock lock = new ReentrantLock();
    static int state = 0;
    static Condition conditionA = lock.newCondition();
    static Condition conditionB = lock.newCondition();
    static Condition conditionC = lock.newCondition();

    public static void main(String[] args){
        Thread thread1 = new Thread(()->{
            for(int i = 0;i<10;i++){
                lock.lock();
                try{
                    while(state%3!=0){
                        try {
                            conditionA.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("A");
                    state++;
                    conditionB.signal();
                }
                finally {
                    lock.unlock();
                }
            }
        });

        Thread thread2 = new Thread(()->{
            for(int i = 0;i<10;i++){
                lock.lock();
                try{
                    while(state%3!=1){
                        try {
                            conditionB.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("B");
                    state++;
                    conditionC.signal();
                }
                finally {
                    lock.unlock();
                }
            }

        });

        Thread thread3 = new Thread(()->{
            for(int i = 0;i<10;i++){
                lock.lock();
                try{
                    while(state%3!=2){
                        try {
                            conditionC.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("C");
                    state++;
                    conditionA.signal();
                }
                finally {
                    lock.unlock();
                }
            }

        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
