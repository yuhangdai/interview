package com.aotain.demo.consumer;

/**
 * Demo class
 *
 * @author bang
 * @date 2019/08/23
 */
public interface ICustom {
    /**
     * 处理kafka消失
     * @param msg
     * @return
     */
    void dealMessage(String msg);
}
