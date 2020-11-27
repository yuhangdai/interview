package com.aotain.singleton;

/**
 * 饱汉模式
 *
 * @author bang
 * @date 2020/11/27
 */
public class Singleton {

    private static Singleton instance = null;

    private Singleton(){ }

    public static Singleton getInstance(){
        if (instance == null) {
            return new Singleton();
        }
        return instance;
    }
}
