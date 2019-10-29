package com.aotain.string;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2019/07/05
 */
public class StringMatter {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        String str2 = new StringBuilder("java").toString();

        System.out.println(str1.intern()==str1);
        System.out.println(str2.intern()==str2);

        System.out.println("======================");
        String s3 = "abcd";
        String s4 = "a"+"b"+"c"+"d";
        String s5 = new String("abcd");
        String s6 = new String("abcd").intern();
        // true
        System.out.println(s3==s4);
        // false
        System.out.println(s3==s5);
        // true
        System.out.println(s3==s6);

    }
}
