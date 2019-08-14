package com.aotain.error;

/**
 * @author bang
 * @date 2018/7/19 21:59
 */
public class LocalVarGC {

    public void localVarGc1(){
        byte[] a = new byte[6*1024*1024];
        System.gc();
    }

    public void localVarGc2(){
        byte[] a = new byte[6*1024*1024];
        a = null;
        System.gc();
    }

    public void localVarGc3(){
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        System.gc();
    }

    public void localVarGc4(){
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        int c = 10;
        System.gc();
    }

    public void localVarGc5(){
        localVarGc1();
        System.gc();
    }

    public static void main(String[] args) {
        LocalVarGC ins = new LocalVarGC();
        ins.localVarGc2();
    }

}
