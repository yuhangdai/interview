package com.aotain.singleton;

/**
 * Demo class
 *
 * @author Administrator
 * @date 2020/05/11
 */
public class SingletonDemo2 {

    private static SingletonDemo2 instance = new SingletonDemo2();

    private SingletonDemo2(){ }

    public static SingletonDemo2 getInstance(){
        return instance;
    }
}
