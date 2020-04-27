package com.aotain.list;

import com.google.common.collect.Lists;

import javax.swing.text.InternationalFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 用于比较大数据量下ArrayList和Linklist不同遍历方式效率
 *
 * @author Administrator
 * @date 2020/04/27
 */
public class ListTraverse {

    public static void main(String[] args) {
        /*
        1587980461948
        1587980461952
        array list for i used 4 ms
        1587980461955
        1587980461957
        array list iterator used 2 ms
        1587980461958
        1587980463786
        linked list for i used 1828 ms
        1587980463786
        1587980463788
        linked list iterator used 2 ms
         */
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i=0;i<50000;i++){
            arrayList.add(i);
            linkedList.add(i);
        }

        //使用不同方式遍历arrayList
        long begin = System.currentTimeMillis();
        for (int i = 0; i < arrayList.size(); i++) {
            int result = arrayList.get(i);
        }
        long end = System.currentTimeMillis();
        System.out.println(begin);
        System.out.println(end);
        System.out.println("array list for i used "+(end-begin)+" ms");

        long begin1 = System.currentTimeMillis();
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            int result = iterator.next();
        }
        long end1 = System.currentTimeMillis();
        System.out.println(begin1);
        System.out.println(end1);
        System.out.println("array list iterator used "+(end1-begin1)+" ms");

        //使用不同方式遍历arrayList
        long begin2 = System.currentTimeMillis();
        for (int i = 0; i < linkedList.size(); i++) {
            int result = linkedList.get(i);
        }
        long end2 = System.currentTimeMillis();
        System.out.println(begin2);
        System.out.println(end2);
        System.out.println("linked list for i used "+(end2-begin2)+" ms");

        long begin3 = System.currentTimeMillis();
        iterator = linkedList.iterator();
        while (iterator.hasNext()){
            int result = iterator.next();
        }
        long end3 = System.currentTimeMillis();
        System.out.println(begin3);
        System.out.println(end3);
        System.out.println("linked list iterator used "+(end3-begin3)+" ms");

        long begin4 = System.currentTimeMillis();
        linkedList.forEach(item->{

        });
        long end4 = System.currentTimeMillis();
        System.out.println(begin4);
        System.out.println(end4);
        System.out.println("linked list foreach used "+(end4-begin4)+" ms");
    }
}
