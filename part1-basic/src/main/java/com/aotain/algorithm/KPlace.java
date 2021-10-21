package com.aotain.algorithm;

/**
 * 查找两个有序数组中第k大的元素
 *
 * @author bang
 * @date 2021/01/15
 */
public class KPlace {

    public static void main(String[] args) {
        int[] a = new int[]{1,3};
        int[] b = new int[]{2};
        System.out.println(findMedianSortedArrays(a,b));;
    }

    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (n>m){
            return findMedianSortedArrays(nums2,nums1);
        }

        return (findKth(nums1,0,nums2,0,(m+n+1)/2) + findKth(nums1,0,nums2,0,(m+n+2)/2))/2.0;
    }

    private static int findKth(int[] m,int i,int[] n,int j,int k){
        if (i >= m.length){
            return n[j+k-1];
        }
        if (j >= n.length){
            return m[i+k-1];
        }
        if (k == 1){
            return Math.min(m[i],n[j]);
        }
        int mid1 = (i + k/2 -1 < m.length) ? m[i + k/2 -1] : Integer.MAX_VALUE;
        int mid2 = (j + k/2 -1 < n.length) ? n[j + k/2 -1] : Integer.MAX_VALUE;
        if (mid1 < mid2){
            return findKth(m,i+k/2,n,j,k - k/2);
        } else {
            return findKth(m,i,n,j+k/2,k - k/2);
        }
    }
}
