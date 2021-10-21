package com.aotain.algorithm;

import java.util.ArrayList;

/**
 * Demo class
 *
 * @author bang
 * @date 2021/01/19
 */
public class RemoveListNnode {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode.next = listNode2;
        removeNthFromEnd2(listNode,2);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ArrayList<ListNode> arrayList = new ArrayList<>();

        arrayList.add(head);
        ListNode prev = head;
        ListNode node;
        while ((node = prev.next) != null){
            arrayList.add(node);
            prev = node;
        }

        if (arrayList.size() == 1 && n == 1){
            return null;
        }
        int prePosition = arrayList.size()-n;
        if (prePosition == 0){
            return arrayList.get(arrayList.size()-n+1);
        }
        ListNode listNode = arrayList.get(prePosition-1);
        ListNode next = listNode.next;
        ListNode nextTwo = next.next;
        listNode.next = nextTwo;
        return head;
    }

    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode p1 = head;
        ListNode p2 = head;
        while(n-- > 0){
            p2 = p2.next;
        }
        if (p2 == null){
            return head.next;
        }
        if ((p2 = p2.next)!=null){
            p1 = p1.next;
        }
        ListNode temp = (p1.next).next;
        p1.next = temp;
        return head;
    }
}
