package com.aotain.singleton;

/**
 * Demo class
 *
 * @author Administrator
 * @date 2020/05/11
 */
public class SingletonDemo3 {

    private static SingletonDemo3 instance = null;

    private SingletonDemo3(){}

    public static SingletonDemo3 getInstance(){
        if (instance == null){
            synchronized (SingletonDemo3.class){
                if (instance == null) {
                    instance = new SingletonDemo3();
                }
            }
        }
        return instance;
    }
}
