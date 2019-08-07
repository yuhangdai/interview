package com.aotain.proxy.statics;

import com.aotain.proxy.ILogger;
import com.aotain.proxy.LoggerImpl;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2019/08/07
 */
public class Main {

    public static void main(String[] args) {
        ILogger logger = new LoggerImpl();
        LoggerProxy loggerProxy = new LoggerProxy(logger);
        loggerProxy.logger();
    }
}
