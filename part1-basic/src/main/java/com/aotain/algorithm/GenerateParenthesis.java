package com.aotain.algorithm;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Demo class
 *
 * @author bang
 * @date 2021/01/20
 */
public class GenerateParenthesis {

    public static void main(String[] args) {
        System.out.println("()()   (()) ");
        System.out.println("()()() (())() (()()) ()(()) ((())) ");

        List<String> stringList = generateParenthesis(2);
        System.out.println(stringList);
    }

    public static List<String> generateParenthesis(int n) {
        List<String> resultList = new ArrayList<>();
        if (n == 1){
            resultList.add("()");
            return resultList;
        }
        List<String> stringList = generateParenthesis(n-1);

        for (int i = 0; i < stringList.size(); i++) {
            String str = stringList.get(i);
            // 获取每一个str插入数据后的结果


        }
        return resultList;
    }


    private void aaa(){

    }
}
