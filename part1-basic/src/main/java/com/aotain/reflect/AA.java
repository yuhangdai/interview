package com.aotain.reflect;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/12/01
 */
public class AA extends A{

    public static void main(String[] args) {
        boolean result1 = AA.class.isAssignableFrom(A.class);
        boolean result2 = A.class.isAssignableFrom(AA.class);
        System.out.println(result1+"==="+result2);
    }
}
