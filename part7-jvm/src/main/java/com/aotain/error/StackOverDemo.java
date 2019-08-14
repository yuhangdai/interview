package com.aotain.error;

/**
 * @author bang
 * @date 2018/7/19 21:05
 */
public class StackOverDemo {

    private static int count=0;

    public static void recursion(){
        count++;
        recursion();
    }

    public static void main(String[] args) {
        try{
            recursion();
        } catch (Exception e){
            System.out.println("deep of calling="+count);
            e.printStackTrace();
        }

    }
}
