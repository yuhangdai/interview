package com.aotain.java8.lambda;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/08/06
 */
public class PredicateTest {

    public static void main(String[] args) {
        Predicate<List> predicate = (list)-> {
            return list != null && !list.isEmpty();
        };
        List<String> stringList = Lists.newArrayList();
        stringList.add("1");
//        boolean result = predicate.test(stringList);
//        System.out.println(result);

        Predicate<List> predicate2 = (list)-> {
            return list.size() == 1;
        };
        // predicate3需要满足predicate和predicate2两个断言
        Predicate<List> predicate3 = predicate.and(predicate2);
        boolean result = predicate3.test(stringList);
        System.out.println(result);

    }
}
