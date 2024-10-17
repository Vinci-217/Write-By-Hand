package org.vinci.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 自定义代理逻辑
public class DynamicProxy implements InvocationHandler {

    // 代理目标
    private Object target;

    // 构造器
    public DynamicProxy(Object target){
        this.target = target;
    }

    // 代理方法实现
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before method: " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("After method: " + method.getName());
        return result;
    }

    // 获取代理对象
    public static Object getProxy(Object target){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new DynamicProxy(target)
        );
    }

}
