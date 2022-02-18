package com.aotain.list;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * Demo class
 *
 * @author daiyu
 * @date 2021/12/20
 */
public class ListExpansion {

    public static void main(String[] args) {
        ArrayList arrayList = Lists.newArrayList();
        for (int i = 0; i < 30; i++) {
            arrayList.add(i);
        }
    }
}
