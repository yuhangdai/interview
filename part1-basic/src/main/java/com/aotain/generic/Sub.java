package com.aotain.generic;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2019/08/09
 */
public class Sub extends Super<String>{
    @Override
    String test() {
        return super.test();
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.test();
    }
}
