package com.aotain.basic;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2019/08/07
 */
public class SpecialNull {

    public static void hello(){
        System.out.println("hello");
    }


    public static void main(String[] args) {

        // 正常通过 输出hello
        ((SpecialNull)null).hello();
        // java.lang.NullPointerException
        ((Integer)null).intValue();
    }
}
