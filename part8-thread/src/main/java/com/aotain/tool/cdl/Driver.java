package com.aotain.tool.cdl;

import java.util.concurrent.CountDownLatch;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/07/28
 */
public class Driver {

    private static final int N = 10;
    private static CountDownLatch startSignal = new CountDownLatch(1);
    private static CountDownLatch doneSignal = new CountDownLatch(N);

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            Worker worker = new Worker(startSignal,doneSignal);
            new Thread(worker).start();
        }

        doSomething();
        startSignal.countDown();
        doSomething();
        try {
            doneSignal.await();
            System.out.println("finish work");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void doSomething(){
        System.out.println("driver do something");
    }


    static class Worker implements Runnable{

        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        public Worker(CountDownLatch startSignal,CountDownLatch doneSignal){
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {

            try {
                startSignal.await();
                //
                doWork();
                doneSignal.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doWork(){
            System.out.println("worker do thing");
        }
    }
}
