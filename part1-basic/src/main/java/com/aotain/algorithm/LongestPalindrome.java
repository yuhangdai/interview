package com.aotain.algorithm;

import com.google.common.collect.Lists;
import com.sun.org.apache.xerces.internal.xs.StringList;
import org.junit.platform.commons.util.StringUtils;

import java.util.List;

/**
 * 最长回文子串
 *
 * @author bang
 * @date 2021/01/15
 */
public class LongestPalindrome {

    // bacabab  abcba    ccccccc
    public static void main(String[] args) {
        LongestPalindrome palindrome = new LongestPalindrome();
//        if (palindrome.ishw("abcba")){
//            System.out.println("abcba");
//        }
        List<String> stringList = palindrome.getAllPalindromes("bacabab");
        System.out.println(palindrome.getLongestStr(stringList));
    }

    private String getLongestStr(List<String> stringList){
        String str = "";
        for (int i = 0; i < stringList.size(); i++) {
            String indexi = stringList.get(i);
            if (indexi.length()>str.length()){
                str = indexi;
            }
        }
        return str;
    }

    private List<String> getAllPalindromes(String str){
        List<String> stringList = Lists.newArrayList();
        if (str == null || str == ""){
            return stringList;
        }
        if (str.length()<=2){
            return getLess2Palindrome(str);
        }

        String i0 = str.substring(0,1);
        String i1 = str.substring(1,2);
        String i2 = str.substring(2,3);

        String substr = "";
        if (!i0.equals(i1)){
            if (i2.equals(i0)){
                StringBuilder stringBuilder = new StringBuilder("");
                stringBuilder.append(i0);
                stringBuilder.append(i1);
                stringBuilder.append(i2);
                stringList.add(stringBuilder.toString());
                substr = str.substring(3,str.length());
            } else {
                substr = str.substring(1,str.length());

            }
        } else {
            StringBuilder stringBuilder = new StringBuilder("");
            if (!i2.equals(i0)){
                stringBuilder.append(i0);
                stringBuilder.append(i1);
                stringList.add(stringBuilder.toString());
                substr = str.substring(2,str.length());

            } else {
                stringBuilder.append(i0);
                stringBuilder.append(i1);
                stringBuilder.append(i2);
                if (3 == str.length()){
                    stringList.add(stringBuilder.toString());
                    return stringList;
                }

                for (int i = 3; i < str.length(); i++) {
                    String indexi = str.substring(i,i+1);
                    if (indexi.equals(i0)){
                        stringBuilder.append(indexi);
                        if (i == str.length()-1){
                            stringList.add(stringBuilder.toString());
                            return stringList;
                        }
                    } else {
                        stringList.add(stringBuilder.toString());
                        substr = str.substring(i,str.length());
                        break;
                    }
                }
            }
        }
        stringList.addAll(getAllPalindromes(substr));
        return stringList;
    }

    private List<String> getLess2Palindrome(String str){
        List<String> stringList = Lists.newArrayList();
        if (str == null || str == ""){
            return stringList;
        }
        if (str.length() == 1){
            stringList.add(str);
            return stringList;
        }
        String i0 = str.substring(0,1);
        String i1 = str.substring(1,2);
        if (str.length() == 2){
            if (i0.equals(i1)) {
                stringList.add(str);
            } else {
                stringList.add(i0);
                stringList.add(i1);
            }
        }
        return stringList;
    }

    private boolean ishw(String str){
        for (int i = 0; i < str.length(); i++) {
            String indexi = str.substring(i,i+1);
            String indexj = str.substring(str.length()-1-i,str.length()-i);
            if (!indexi.equals(indexj) ){
                return false;
            }
            if (i >= str.length()-i){
                return true;
            }
        }
        return true;
    }
}
