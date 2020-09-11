package com.aotain.proxy.dynamic.jdk;

import com.aotain.proxy.AMapperImpl;
import com.aotain.proxy.ICommonMapper;
import com.aotain.proxy.ILogger;
import com.aotain.proxy.LoggerImpl;

import java.lang.reflect.Proxy;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2019/08/07
 */
public class Main {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        ILogger logger = new LoggerImpl();
        DynamicInvocationHandler dynamicInvocationHandler = new DynamicInvocationHandler(logger);
        ILogger loggerImpl = (ILogger) Proxy.newProxyInstance(Main.class.getClassLoader(),new Class[]{ILogger.class}, dynamicInvocationHandler);
        loggerImpl.hashCode();

        ICommonMapper commonMapper = new AMapperImpl();
        dynamicInvocationHandler = new DynamicInvocationHandler(commonMapper);
        ICommonMapper iCommonMapper = (ICommonMapper) Proxy.newProxyInstance(Main.class.getClassLoader(),new Class[]{ICommonMapper.class}, dynamicInvocationHandler);
        iCommonMapper.save(new Integer(1));

        System.out.println("$Proxy0.class全名: "+ Proxy.getProxyClass(Main.class.getClassLoader(),new Class[]{ILogger.class}));
    }
}
