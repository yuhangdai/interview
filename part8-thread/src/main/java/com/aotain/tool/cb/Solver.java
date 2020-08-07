package com.aotain.tool.cb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/07/29
 */
public class Solver {

    final int N;
    final float[][] data;
    final CyclicBarrier barrier;

    class Worker implements Runnable {
        int myRow;

        Worker(int row) {
            myRow = row;
        }

        @Override
        public void run() {
            while (!done()) {
                processRow(myRow);

                try {
                    barrier.await();
                } catch (InterruptedException ex) {
                    return;
                } catch (BrokenBarrierException ex) {
                    return;
                }
            }
        }

        private boolean done(){
            return false;
        }

        public void processRow(int row){

        }
    }

    public Solver(float[][] matrix) {
        data = matrix;
        N = matrix.length;
        Runnable barrierAction =
                new Runnable() {
                    @Override
                    public void run() {
                        mergeRows();
                    }
                };
        barrier = new CyclicBarrier(N, barrierAction);

        List<Thread> threads = new ArrayList<Thread>(N);
        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(new Worker(i));
            threads.add(thread);
            thread.start();
        }

        // wait until done
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void mergeRows(){

    }

}
