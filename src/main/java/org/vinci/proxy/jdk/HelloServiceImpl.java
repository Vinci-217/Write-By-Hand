package org.vinci.proxy.jdk;

// 目标类实现接口
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("Hello!");
    }
}


