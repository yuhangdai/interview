package com.aotain.jad.traverse;

/**
 * foreach遍历编译前后代码
 *
 * @author Administrator
 * @date 2020/04/22
 */
public class TraverseDemo {

    public void beforeCompile(){
        int[] a = {1,2,3};
        for (int ai:a) {
            System.out.println(ai);
        }
    }

    public void afterCompile(){
        int[] a = {1,2,3};
        for (int ai:a) {
            System.out.println(ai);
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3};
        for (int ai:a) {
            System.out.println(ai);
        }
    }
}
