package com.aotain;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<String> list = Lists.newArrayList();
        list.add("1");
        list.add("2");
        System.out.println(list.toString().replace("[","").replace("]",""));
    }
}
