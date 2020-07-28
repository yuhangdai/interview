package com.aotain.singleton;

/**
 * Demo class
 *
 * @author Administrator
 * @date 2020/05/11
 */
public class SingletonDemo1 {

    private static SingletonDemo1 instance = null;

    private SingletonDemo1(){ }

    public static SingletonDemo1 getInstance(){
        if (instance != null){
            return instance;
        }
        return new SingletonDemo1();
    }
}
