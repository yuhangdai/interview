package com.aotain.map;

import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2019/08/07
 */
public class HashMapTest {

    public static void main(String[] args) {
        // 此时只调用构造函数，并未初始化对应的Node<K,V>数组
        Map<String,String> map = Maps.newHashMapWithExpectedSize(10);
        // 初始化节点数组，并将值插入其中
        map.put("1","1");
        try {
            Field filed = map.getClass().getDeclaredField("table");
            filed.setAccessible(true);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

//        System.out.println(map.size());
        System.out.println(1 | 1 );
        System.out.println(1 | 0 );
        System.out.println(0 | 1 );
        System.out.println(0 | 0 );

    }
}
