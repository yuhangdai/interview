package com.aotain.algorithm;

/**
 * Demo class
 *
 * @author bang
 * @date 2021/01/29
 */
public class RemoveElement {

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,2,1};
        System.out.println(removeElement(nums,1));;
    }

    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0){
            return 0;
        }
        int i = 0;
        int j = nums.length-1;

        int length = nums.length;
        while (i<=j){
            if (i == j && nums[i] == val){
                length--;
                break;
            }
            if (nums[i] == val){
                if (nums[j] != val){
                    nums[i] = nums[j];
                    i++;
                    j--;
                    length--;
                } else {
                    j--;
                    length--;
                }

            } else {
                i++;
            }

        }
        return length;
    }
}
