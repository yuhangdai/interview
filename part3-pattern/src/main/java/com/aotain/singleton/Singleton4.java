package com.aotain.singleton;

/**
 * 静态内部类单例模式
 * 静态内部类单例模式也称单例持有者模式，实例由内部类创建，
 * 由于 JVM 在加载外部类的过程中, 是不会加载静态内部类的,
 * 只有内部类的属性/方法被调用时才会被加载, 并初始化其静态属性。
 * 静态属性由static修饰，保证只被实例化一次，并且严格保证实例化顺序
 * @author bang
 * @date 2020/11/27
 */
public class Singleton4 {

    private Singleton4(){ }

    static class InstanceHolder{
        private static Singleton4 instance = new Singleton4();
    }

    public static Singleton4 getInstance(){
        return InstanceHolder.instance;
    }
}
