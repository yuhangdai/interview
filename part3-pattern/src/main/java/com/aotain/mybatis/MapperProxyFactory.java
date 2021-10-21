package com.aotain.mybatis;

import java.lang.reflect.Proxy;

/**
 * Demo class
 *
 * @author bang
 * @date 2021/01/27
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    protected T newInstance(MapperProxy<T> mapperProxy) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, mapperProxy);
    }

    public T newInstance() {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(mapperInterface);
        return newInstance(mapperProxy);
    }
}
