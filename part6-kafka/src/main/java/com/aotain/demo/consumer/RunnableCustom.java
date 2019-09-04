package com.aotain.demo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;

/**
 * kafka消费线程类
 *
 * @author bang
 * @date 2019/08/23
 */
public class RunnableCustom implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(RunnableCustom.class);

    /** 每个线程对应一个KafkaConsumer **/
    private final KafkaConsumer<String,String> consumer;

    private String topic;

    private final ICustom custom;

    /**
     * 初始化kafka公共属性
     */
    public static void initCommonPro(Properties properties){
        // 自动提交位移
        properties.put("enable.auto.commit","true");
        properties.put("auto.commit.interval.ms","1000");
        properties.put("session.timeout.ms","30000");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
    }

    public RunnableCustom(String brokerList, String groupId, String topic, ICustom custom) {
        Properties properties = new Properties();
        initCommonPro(properties);
        properties.put("bootstrap.servers",brokerList);
        properties.put("group.id",groupId);
        this.consumer = new KafkaConsumer<>(properties);

        this.custom = custom;
        this.topic = topic;

    }

    @Override
    public void run() {
        consumer.subscribe(Arrays.asList(topic));
        while(true){
            ConsumerRecords<String,String> records = consumer.poll(1000);
            for (ConsumerRecord<String,String> record:records){
                logger.info(Thread.currentThread().getName() + " consumed "
                        + record.partition()+ " the message with offsets: " +record.offset());
                custom.dealMessage(record.value());
            }
        }
    }

    public void shutdown(){
        consumer.wakeup();
    }
}
