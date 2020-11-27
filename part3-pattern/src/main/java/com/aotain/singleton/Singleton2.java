package com.aotain.singleton;

/**
 * 饿汉模式
 *
 * @author bang
 * @date 2020/11/27
 */
public class Singleton2 {

    private static Singleton2 instance = new Singleton2();

    private Singleton2(){ }

    public static Singleton2 getInstance(){
        return instance;
    }
}
