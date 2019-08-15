package com.aotain.error;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2019/07/04
 */
public class HeapStackError {

    private static List<Integer[]> integerList = Lists.newArrayList();

    public static void main(String[] args) {
        while(true){
            integerList.add(new Integer[10000]);
        }
    }
}
