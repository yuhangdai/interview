package com.aotain.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Demo class
 *
 * @author bang
 * @date 2021/01/19
 */
public class ValidStr {

    public static void main(String[] args) {
        System.out.println(isValid("{}{}()[]{{}}"));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i <= chars.length; i++) {
            char str = chars[i];
            if (str == '[' || str == '{' || str == '('){
                stack.add(str);
            } else {
                if (stack.isEmpty()){
                    return false;
                }
                char str2 = stack.pop();
                if (!isMatch(str,str2)){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isMatch(char begin,char end){
        boolean b1 = (begin == '(' && end == ')') || (begin == ')' && end == '(');
        boolean b2 = (begin == '{' && end == '}') || (begin == '}' && end == '{');
        boolean b3 = (begin == '[' && end == ']') || (begin == ']' && end == '[');
        return b1 || b2 ||b3;
    }


}
