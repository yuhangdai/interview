package com.aotain.java8;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/08/06
 */
public class Main {

    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        System.out.println(formula.calculate(10));
        System.out.println(formula.sqrt(100));
    }
}
