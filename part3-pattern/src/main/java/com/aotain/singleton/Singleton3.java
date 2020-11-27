package com.aotain.singleton;

/**
 * 双重检查锁机制
 *
 * @author bang
 * @date 2020/11/27
 */
public class Singleton3 {

    private volatile static Singleton3 instance;

    private Singleton3(){}

    private static Singleton3 getInstance(){

        if (null == instance){
            synchronized (Singleton3.class){
                if (null == instance){
                    instance = new Singleton3();
                }
            }
        }

        return instance;
    }
}
