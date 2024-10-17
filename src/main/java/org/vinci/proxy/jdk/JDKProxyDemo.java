package org.vinci.proxy.jdk;

public class JDKProxyDemo {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloService proxy = (HelloService) DynamicProxy.getProxy(helloService);
        proxy.sayHello();
    }
}
