package org.vinci.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

// 自定义代理逻辑
public class CglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before method: " + method.getName());
        Object result = methodProxy.invokeSuper(obj, args);
        System.out.println("After method: " + method.getName());
        return result;
    }

    // 获取代理对象
    public static Object getProxy(Class<?> targetClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(new CglibProxy());
        return enhancer.create();
    }
}
