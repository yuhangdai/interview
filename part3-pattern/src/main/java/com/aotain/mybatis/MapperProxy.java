package com.aotain.mybatis;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Demo class
 *
 * @author bang
 * @date 2021/01/27
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private final Class<T> mapperInterface;

    public MapperProxy(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (Object.class.equals(method.getDeclaringClass())) {
                // Object方法直接调用
                return method.invoke(this, args);
            } else {
                // 其它方法调用cachedInvoker
                System.out.println("call the proxy object method");
                return method.invoke(proxy,args);
            }
        } catch (Throwable t) {
            throw new RuntimeException("");
        }
    }
}
