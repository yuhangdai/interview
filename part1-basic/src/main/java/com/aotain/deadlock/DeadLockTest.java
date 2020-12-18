package com.aotain.deadlock;

import lombok.Synchronized;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/12/17
 */
public class DeadLockTest {

    private static Object obj1 = new Object();
    private static Object obj2 = new Object();

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getO1Lock();
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                getO2Lock();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread thread2 = new Thread(runnable2);
        thread2.start();
//        thread2.sleep(3000);
    }

    private static void getO1Lock(){

        synchronized (obj1){
            Object o = obj2;
        }

    }

    private static void getO2Lock(){
        synchronized (obj2){
            Object o = obj1;
        }
    }
}
