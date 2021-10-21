package com.aotain.algorithm;

/**
 * Demo class
 *
 * @author bang
 * @date 2021/01/29
 */
public class ContainStr {

    public static void main(String[] args) {
        System.out.println(strStr("acbdd","bd"));
    }

    public static int strStr(String haystack, String needle) {
        if ("".equals(needle) ){
            return 0;
        }
        if (haystack == null || needle == null){
            return -1;
        }
        return haystack.indexOf(needle);
    }
}
