package com.aotain.error;

/**
 * 非逃逸对象栈上分配demo
 *  对于大量的零散小对象，栈上分配提供了一种很好的优化策略，栈上分配速度快，
 *      并且可以有效避免垃圾回收带来的负面影响，但由于栈空间较小，因此对于大对象不适合栈上分配
 * @author bang
 * @date 2018/7/19 22:10
 */
public class OnStackDemo {
    public static class User{
        public int id=0;
        public String name="";
    }

    public static void alloc(){
        User u = new User();
        u.id = 5;
        u.name = "geym";
    }

    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        for (int i=0;i<100000000;i++){
            alloc();
        }
        long e = System.currentTimeMillis();
        System.out.println(e-b);
    }
}
