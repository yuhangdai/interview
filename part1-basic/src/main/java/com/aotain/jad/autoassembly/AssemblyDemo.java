package com.aotain.jad.autoassembly;

import com.aotain.jad.AbstractDemo;

/**
 * 自动拆装箱处理机制
 *
 * @author Administrator
 * @date 2020/04/22
 */
public class AssemblyDemo extends AbstractDemo {

    @Override
    public void beforeCompile() {
        int a = new Integer(10);
        Integer b = 10;
    }

    @Override
    public void afterCompile() {
        // 使用intValue()将Integer转为int
        int a = (new Integer(10)).intValue();
        // 使用valueOf()将int转为Integer
        Integer b = Integer.valueOf(10);
    }
}
