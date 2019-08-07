package com.aotain.proxy.statics;

import com.aotain.proxy.ILogger;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2019/08/07
 */
public class LoggerProxy implements ILogger {

    private ILogger logger;

    public LoggerProxy(ILogger logger){
        this.logger = logger;
    }

    @Override
    public void logger() {
        System.out.println("before log...");
        logger.logger();
        System.out.println("after log...");
    }
}
