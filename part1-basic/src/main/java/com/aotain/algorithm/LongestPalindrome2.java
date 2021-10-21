package com.aotain.algorithm;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 最长回文子串
 *
 * @author bang
 * @date 2021/01/15
 */
public class LongestPalindrome2 {
    //#h#n#g#f#  #h#n#g#
    public static void main(String[] args) {
        System.out.println(getMaxPalindromesLength("bacabab"));
    }

    private static String fillWithSpe(String str){
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("#");
        for (int i = 0; i < str.length(); i++) {
            stringBuilder.append(str.charAt(i)).append("#");
        }
        return stringBuilder.toString();
    }

    private static int getMaxPalindromesLength(String str){
        char[] chars = fillWithSpe(str).toCharArray();
        int maxLength = 0;
        for (int i = 0; i < chars.length; i++) {
            maxLength=Math.max(subpalidromelen(chars,i), maxLength);
        }
        return maxLength;
    }

    public static int subpalidromelen(char []chs,int i){
        int len = 0;
        for (int j = 0; j<=i && j < chs.length-i; j++) {
            if (chs[i-j] == chs[i+j]){
                len++;
            } else {
                break;
            }
        }
        return len-1;
    }


}
