package com.aotain.basic;

/**
 * Demo class
 *
 * @author daiyu
 * @date 2021/12/18
 */
public class BasicAutoPackage {

    private String s;
    private int i;

    public static void main(String[] args) {
        BasicAutoPackage basicAutoPackage = new BasicAutoPackage();
        basicAutoPackage.print();
    }

    private void print(){
        System.out.println(s+"==="+"i="+i);
    }
}
