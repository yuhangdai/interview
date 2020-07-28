package com.aotain.thread;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/07/27
 */
public class ThreadPoolExecutorsMain {

    private static final int CORE_SIZE = 5;
    private static final int MAX_SIZE = 10;

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_SIZE,MAX_SIZE,60, TimeUnit.SECONDS,new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 12; i++) {
            MyRunnable myRunnable = new MyRunnable(""+i);
            threadPoolExecutor.execute(myRunnable);
        }

        //终止线程池
        threadPoolExecutor.shutdown();

        while (!threadPoolExecutor.isTerminated()) {
        }
    }
}
