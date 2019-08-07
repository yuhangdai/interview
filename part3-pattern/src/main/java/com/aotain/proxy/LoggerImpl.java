package com.aotain.proxy;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2019/08/07
 */
public class LoggerImpl implements ILogger{

    @Override
    public void logger() {
        System.out.println("record logger...");
    }
}
