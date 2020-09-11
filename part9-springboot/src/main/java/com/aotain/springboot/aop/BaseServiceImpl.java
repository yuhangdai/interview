package com.aotain.springboot.aop;

import org.springframework.stereotype.Service;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/09/03
 */
public class BaseServiceImpl implements IBaseService{

    @Override
    public void hello() {
        System.out.println("call the hello method");
    }
}
