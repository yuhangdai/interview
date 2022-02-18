package com.aotain.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * Demo class
 *
 * @author daiyu
 * @date 2021/12/20
 */
public class ThreadJoinTest {

    public static void main(String[] args) {
        try {
//            Thread thread = new Thread(new MyRunnable("111"));
//            thread.start();
//            thread.join();
            LockSupport.park();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("main thread end");
    }
}
