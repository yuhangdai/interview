package com.aotain.basic;

/**
 * @author bang
 * @date 2019/8/8 20:04
 */
public class Compare {

    public static void main(String[] args) {
        String str1 = "hello world";
        String str2 = "hello "+"world";
        System.out.println(str1==str2);  //true

        Integer a1 = 200;  // Integer a1 = new Integer(200);
        Integer a2 = 200;  // Integer a2 = new Integer(200);
        System.out.println(a1==a2); //false

        String str3 = "hello";
        String str4 = new String("hello");
        System.out.println(str3==str4);  // false
    }
}
