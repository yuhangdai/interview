package com.aotain.java8;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/08/06
 */
public interface Formula {

    double calculate(int a);

    /**
     * 接口默认方法
     * @param a
     * @return
     */
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
