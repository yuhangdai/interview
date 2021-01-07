package com.aotain.basic;

import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * Demo class
 *
 * @author Administrator
 * @date 2020/04/21
 */
public class EqualsTip {

    @Test
    public void test2(){
        Integer a1 = 2;
        Integer a2 = 2;

        int a3 = 2;
        int a4 = 2;

        Integer a5 = new Integer(2);
        // true
        // 当使用自动装箱方式创建一个Integer对象时，
        // 当数值在-128 ~127时，会将创建的 Integer 对象缓存起来，当下次再出现该数值时，
        // 直接从缓存中取出对应的Integer对象。所以上述代码中，x和y引用的是相同的Integer对象
        System.out.println(a1 == a2);
        // true
        System.out.println(a3 == a4);
        // true
        System.out.println(a1 == a3);
        // false
        System.out.println(a1 == a5);
        // true
        System.out.println(Objects.equals(a1,a2));
        // true
        System.out.println(Objects.equals(a1,a3));
    }

    @Test
    public void test3(){
        //对于字符串：其对象的引用都是存储在栈中的，
        // 如果是编译期已经创建好(直接用双引号定义的)的就存储在常量池中，
        // 如果是运行期（new出来的）才能确定的就存储在堆中。对于equals相等的字符串，
        // 在常量池中永远只有一份，在堆中有多份
        String s1 = new String("abc");
        String s2 = "abc";
        String s3 = "ab" + "c";

        String s4 = "ab";
        String s5 = s4 + "c";
        // false
        System.out.println(s1 == s2);
        // false
        System.out.println(s1 == s3);
        // true
        System.out.println(s2 == s3);
        // false
        System.out.println(s2==s5);
    }

    @Test
    public void test4(){
        String a = "hello2";
        String b = "hello";
        String c = b + 2;
        // false
        System.out.println(a == c);

        String d = "helloworld";
        String e = b+"world";
        // false
        System.out.println(d == e);

        String f = "hello"+"world";
        // true
        System.out.println(d == f);
    }
}
