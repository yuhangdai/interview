package com.aotain.set;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Demo class
 *
 * @author daiyu
 * @date 2021/12/20
 */
public class SetTest {

    public static void main(String[] args) {
        Set<Integer> sets = Sets.newTreeSet();

        sets.add(5);
        sets.add(2);
        sets.add(3);
        sets.add(4);
        sets.add(1);

        int index = 0;
        for (Integer value : sets){
            System.out.println("index:"+index+" value is "+value);
            index++;
        }
    }
}
