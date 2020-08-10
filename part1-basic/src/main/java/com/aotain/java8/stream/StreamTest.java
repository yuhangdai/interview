package com.aotain.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/08/10
 */
public class StreamTest {

    public static void main(String[] args) {
        // 去掉为空的字符串并输出
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered);



        // 使用limit限制个数
        strings.stream().limit(5).forEach(System.out::println);

        // 使用foreach遍历流
        Random random = new Random();
        random.ints().limit(10).forEach(n-> System.out.println(n));

        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filteredList = stringList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filteredList);
        String mergedString = stringList.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }
}
