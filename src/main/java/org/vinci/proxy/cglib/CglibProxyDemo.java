package org.vinci.proxy.cglib;

public class CglibProxyDemo {
    public static void main(String[] args) {
        HelloServiceImpl helloService = new HelloServiceImpl();
        HelloServiceImpl proxy = (HelloServiceImpl) CglibProxy.getProxy(helloService.getClass());
        proxy.sayHello();
    }
}
