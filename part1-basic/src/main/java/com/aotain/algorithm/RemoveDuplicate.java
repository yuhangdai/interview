package com.aotain.algorithm;

/**
 * Demo class
 *
 * @author bang
 * @date 2021/01/29
 */
public class RemoveDuplicate {

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {

        if (nums.length == 0){
            return 0;
        }

        int cursor = nums[0];
        int length = 1;
        for (int i = 1; i < nums.length; i++) {
            int index = nums[i];
            if (cursor == index){
                // 相等
                continue;
            } else {
                // 不相等 cursor更改为当前值 长度+1
                nums[length] = index;
                cursor = index;
                length++;
            }

        }
        return length;
    }
}
