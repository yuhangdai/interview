package com.aotain.generic;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2019/08/09
 */
public class GenericDemo {

    public static void test(List<? extends A> a){

    }

    public static void main(String[] args) {
        List<A> aList = new ArrayList<>();
        aList.add(new AA());
        aList.add(new A());

        List<AA> aaList = Lists.newArrayList();
        aaList.add(new AA());
        test(aList);
        test(aaList);



    }
}
