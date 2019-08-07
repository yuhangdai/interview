package com.aotain.proxy.dynamic.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2019/08/07
 */
public class SafetyCheckCallback implements MethodInterceptor{

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before safety check.");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("after safety check.");
        return result;
    }
}
