package com.aotain.algorithm;

import java.util.List;

/**
 * Demo class
 *
 * @author bang
 * @date 2021/01/20
 */
public class MergeList {

    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
//        ListNode[] listNodes = new ListNode[]{l1,l2};
        ListNode[] listNodes = new ListNode[]{};
        ListNode l = mergeKLists(listNodes);
        System.out.println(l);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0){
            return new ListNode();
        }
        if (lists.length == 1){
            return lists[0];
        }
        ListNode node = mergeTwoLists(lists[0],lists[1]);
        for (int i = 1; i < lists.length-1; i++) {
            node = mergeTwoLists(node,lists[i+1]);
        }
        return node;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        ListNode head = new ListNode();
        ListNode cursor = head;
        while(l1!=null || l2!=null){
            ListNode node = null;
            if (l1 == null){
                cursor.next = l2;
                break;
            }
            if (l2 == null){
                cursor.next = l1;
                break;
            }
            if (l1.val < l2.val){
                cursor.next = l1;
                l1 = l1.next;

            } else {
                cursor.next = l2;
                l2 = l2.next;

            }
            cursor = cursor.next;
        }
        return head.next;
    }


}
