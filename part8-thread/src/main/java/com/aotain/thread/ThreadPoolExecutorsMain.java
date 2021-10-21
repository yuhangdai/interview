package com.aotain.thread;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
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

        try{
            for (int i = 0; i < 12; i++) {
                MyRunnable myRunnable = new MyRunnable(""+i);
                threadPoolExecutor.execute(myRunnable);
                long taskCount = threadPoolExecutor.getTaskCount();
                int activeCount = threadPoolExecutor.getActiveCount();
                BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();
                int remainCapacity = queue.remainingCapacity();
                System.out.println("current task size is "+taskCount+",active task size is "+activeCount
                        +",and the queue max capacity is 1,remain capacity is "+remainCapacity);
                if (i == 10){
                    threadPoolExecutor.setMaximumPoolSize(11);
                }
            }
        } catch (Exception e){
            System.out.println("catch exception"+e);
        }


        long taskCount = threadPoolExecutor.getCompletedTaskCount();
        System.out.println("completed task size is "+taskCount);
        //终止线程池
        threadPoolExecutor.shutdownNow();

//        threadPoolExecutor.execute(new MyRunnable("tttt"));

        while (!threadPoolExecutor.isTerminated()) {
            System.out.println("thread pool executor is running");
        }
    }
}
