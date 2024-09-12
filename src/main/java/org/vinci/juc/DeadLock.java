package org.vinci.juc;

/**
 * 线程死锁
 */
public class DeadLock {
    public static Object resource1 = new Object();
    public static Object resource2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + "get resource2");
                }
            }
        }, "线程1").start();

        new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread().getName() + "get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "waiting get resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread().getName() + "get resource1");
                }
            }
        }, "线程2").start();

    }
}
