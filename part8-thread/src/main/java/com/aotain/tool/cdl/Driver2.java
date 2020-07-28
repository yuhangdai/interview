package com.aotain.tool.cdl;

import java.util.concurrent.*;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/07/28
 */
public class Driver2 {

    private static final int CORE_SIZE = 5;
    private static final int MAX_SIZE = 10;

    private static final int N = 10;
    private static CountDownLatch doneSignal = new CountDownLatch(N);

    public static void main(String[] args) {
        Executor executor = new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE,
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < N; i++) {
            executor.execute(new WorkRunnable(doneSignal,i));
        }
        try {
            doneSignal.await();
            System.out.println("finish all "+N+" works");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class WorkRunnable implements Runnable{

        private final int i;
        private final CountDownLatch doneSignal;

        public WorkRunnable(CountDownLatch doneSignal,int i){
            this.doneSignal = doneSignal;
            this.i = i;
        }

        @Override
        public void run() {
            doWork(i);
            doneSignal.countDown();
        }

        public void doWork(int i){
            System.out.println("do work "+i);
        }
    }
}
