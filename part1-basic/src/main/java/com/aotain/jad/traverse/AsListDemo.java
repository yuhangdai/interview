package com.aotain.jad.traverse;

import com.aotain.jad.AbstractDemo;

import java.util.Arrays;

/**
 * 基本类型数组和包装类型数组Arrays.asList()
 *
 * @author Administrator
 * @date 2020/04/22
 */
public class AsListDemo extends AbstractDemo {

    @Override
    public void afterCompile(){
        int[] a = {1,2,3};
        Integer[] a2 = {1,2,3};
        Arrays.asList(a);
        Arrays.asList(a2);
    }

    @Override
    public void beforeCompile() {
        int[] a = { 1, 2, 3 };
        Integer[] a2 = { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3) };
        // Arrays.asList将基本类型数组转化为二维数组
        Arrays.asList(new int[][] { a });
        Arrays.asList(a2);
    }

    public static void main(String[] args) {
        int[] a = {1,2,3};
        Integer[] a2 = {1,2,3};
        Arrays.asList(a);
        Arrays.asList(a2);
    }
}
