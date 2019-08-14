package com.aotain.kafka.consumer.highlevelconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2018/11/13
 */
public class Processer implements ICustomerCallback{

    private static final Logger logger = LoggerFactory.getLogger(Processer.class);

    @Override
    public void callback(int threadnum, int partition, long offset, String message) {
        logger.info("start deal job message! threadnum=" + threadnum + ", message=" + message);

        // 输出message信息
        System.out.println(message);

        logger.info("complete deal job message! threadnum=" + threadnum + ", message=" + message);
    }
}
