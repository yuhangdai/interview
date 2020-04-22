package com.aotain.jad.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Demo class
 *
 * @author Administrator
 * @date 2020/04/22
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Foo {

    String[] value();
    boolean bar();

}
