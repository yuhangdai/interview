package com.aotain.thread;

import java.util.concurrent.Callable;

/**
 * Demo class
 *
 * @author bang
 * @date 2021/01/13
 */
public class MyCallable implements Callable<Integer> {

    private Integer result;

    public void MyCallable(Integer result){
        this.result = result;
    }

    @Override
    public Integer call() throws Exception {
        return result+1;
    }
}
