package com.aotain.kafka.consumer.multiconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * 消费线程类，执行真正的消费任务
 *
 * @author daiyh@aotain.com
 * @date 2018/11/13
 */
public class ConsumerRunnable implements Runnable{

    /** 每个线程对应一个KafkaConsumer **/
    private final KafkaConsumer<String,String> consumer;

    public ConsumerRunnable(String brokerList,String groupId,String topic) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers",brokerList);
        properties.put("group.id",groupId);
        // 自动提交位移
        properties.put("enable.auto.commit","true");
        properties.put("auto.commit.interval.ms","1000");
        properties.put("session.timeout.ms","30000");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        this.consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        while(true){
            ConsumerRecords<String,String> records = consumer.poll(1000);
            for (ConsumerRecord<String,String> record:records){
                System.out.println(Thread.currentThread().getName() + " consumed "
                        + record.partition()+ " the message with offsets: " +record.offset());
            }
        }
    }
}
