package org.vinci.design;

/**
 * 双重检验锁方式实现单例模式
 */
public class Singleton {
    private volatile static Singleton instance ;
    private Singleton(){
        System.out.println("Create Success");
    }

    private static Singleton getInstance(){
        if(instance==null){
            synchronized (Singleton.class){
                if(instance==null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args){
        for(int i = 0;i<10;i++){
            Thread thread = new Thread(()->{
                Singleton instance =  getInstance();
                System.out.println(instance.hashCode());
            });
            thread.start();
        }
    }
}
