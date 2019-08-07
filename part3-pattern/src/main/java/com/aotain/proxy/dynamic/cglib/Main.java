package com.aotain.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Proxy;


/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2019/08/07
 */
public class Main {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Subject.class);
        enhancer.setCallback(new SafetyCheckCallback());
        Subject subject = (Subject) enhancer.create();
        subject.hello();

//        System.out.println("$Proxy0.class全名: "+ Proxy.getProxyClass(Main.class.getClassLoader(),));
    }
}
