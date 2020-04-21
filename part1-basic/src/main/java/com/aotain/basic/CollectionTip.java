package com.aotain.basic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 测试<T> List<T> Arrays.asList(T... a)
 *      Arrays.asList()将数组转换为集合后,底层其实还是数组
 *      Arrays.asList() 方法返回的并不是 java.util.ArrayList ，
 *      而是 java.util.Arrays 的一个内部类,这个内部类并没有实现集合的修改方法或者说并没有重写这些方法
 *
 * 以及<T> T[] Collection.toArray(T[] a)
 *      如果toArray方法中没有传递任何参数的话返回的是Object类型数组
 * @author Administrator
 * @date 2020/04/20
 */
public class CollectionTip {

    @Test
    public void test(){
        // java.lang.UnsupportedOperationException
        List<Integer> result = Arrays.asList(new Integer[]{1,2,3});
        result.add(4);
    }
}
