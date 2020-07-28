package com.aotain.thread;

import java.util.Date;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/07/27
 */
public class MyRunnable implements Runnable{

    private String command;

    public MyRunnable(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" "+command+" is running"+"  ==="+ new Date());
        try{
            Thread.sleep(60000);
        } catch (Exception e){

        }

    }
}
