package org.vinci.juc;

public class TwoThread {

    private static Object lock = new Object();
    private static boolean flag = true;
    public static void main(String[] args){
        Thread thread1 = new Thread(()->{
            for(int i= 0;i<50;i++){
                synchronized (lock){
                    while(flag){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("A");
                    flag = true;
                    lock.notify();
                }
            }
        });
        thread1.start();



        Thread thread2 = new Thread(()->{
            for(int i= 0;i<50;i++){
                synchronized (lock){
                    while(!flag){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("B");
                    flag = false;
                    lock.notify();

                }
            }
        });
        thread2.start();


    }
}
