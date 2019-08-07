package com.aotain.proxy;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2019/08/07
 */
public interface ICommonMapper<T> {

    int save(T t);
    int update(T t);
}
