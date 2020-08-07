package com.aotain.java8.lambda;

import java.util.function.Function;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/08/07
 */
public class FunctionTest {

    public static void main(String[] args) {
        Function<String,Integer> toInteger = (string)->Integer.valueOf(string);
        Function<String,String> backToString = toInteger.andThen(String::valueOf);
        System.out.println(backToString.apply("123"));
        System.out.println(backToString.apply("123").getClass());

        Function<String,Integer> function = toInteger.compose(String::valueOf);
        System.out.println(function.apply("123"));
        System.out.println(function.apply("123").getClass());


    }
}
