package com.aotain.springboot.aop;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/09/03
 */
public class MyAdvice {

    public void before(){
        System.out.println("before method call");
    }

    public void after(){
        System.out.println("after method call");
    }

}
