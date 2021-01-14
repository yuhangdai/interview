package com.aotain.basic;

/**
 * Demo class
 *
 * @author bang
 * @date 2021/01/13
 */
public class ExceptionTest {

    private static String exceptionReturn(String str){
        try{
            return str.length()+"";
        } catch (Exception e){
            return "exception result";
        } finally {
            return "finally result";
        }

    }

    public static void main(String[] args) {
        System.out.println(exceptionReturn("111"));
    }
}
