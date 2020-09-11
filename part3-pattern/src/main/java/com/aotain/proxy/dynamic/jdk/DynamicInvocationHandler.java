package com.aotain.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2019/08/07
 */
public class DynamicInvocationHandler implements InvocationHandler{

    private Object target;// 待代理对象

    public DynamicInvocationHandler(Object object){
        this.target = object;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] args) throws Throwable {
        System.out.println("before method call...");
        Object obj =  method.invoke(target,args);
        System.out.println("after method call...");
        return obj;
    }
}
