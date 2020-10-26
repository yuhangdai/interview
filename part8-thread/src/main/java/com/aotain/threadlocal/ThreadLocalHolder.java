package com.aotain.threadlocal;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/10/26
 */
public class ThreadLocalHolder {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(threadLocal.get());;
            threadLocal.set("thread 1");
            System.out.println(threadLocal.get());;
        }).start();

        new Thread(()->{

            System.out.println(threadLocal.get());;
            threadLocal.set("thread 2");
            System.out.println(threadLocal.get());;
        }).start();
    }

}
