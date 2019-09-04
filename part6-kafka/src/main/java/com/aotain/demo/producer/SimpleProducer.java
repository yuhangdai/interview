package com.aotain.demo.producer;

import com.alibaba.fastjson.JSON;
import com.aotain.nms.common.alarm.AlarmMessage;
import com.aotain.nms.common.alarm.AlarmMessageContent;
import com.aotain.nms.common.utils.numberic.RandomMath;
import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Demo class
 *
 * @author bang
 * @date 2019/08/23
 */
public class SimpleProducer {
    private static Logger logger = LoggerFactory.getLogger(SimpleProducer.class);

    public static void main(String[] args) throws InterruptedException {
        sendMessage();
    }

    private static void sendMessage() throws InterruptedException {
        Producer<String,String> producer = createProducer();
        sendMessages(producer);
        Thread.sleep(20);
    }

    private static Producer<String, String> createProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "server-1:9092,server-2:9092,server-3:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        // Controls how much bytes sender would wait to batch up before publishing to Kafka.
        props.put("batch.size", 10);
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return new KafkaProducer(props);

    }

    private static void sendMessages(Producer<String, String> producer) throws InterruptedException {
        String topic = "nmsalarmmessagequeue";
        int partition = 0;
        long record = 1;
        // 发送消息
        for (int i = 10; i < 20; i++) {
            AlarmMessageContent alarmMessage = new AlarmMessageContent();
            alarmMessage.setAlarm_level(RandomMath.getRangeInt(4,5));
            alarmMessage.setAlarm_message("message:"+i);
            alarmMessage.setAlarm_source("4");
            alarmMessage.setAlarm_time(System.currentTimeMillis()/1000);

            producer.send(new ProducerRecord<String, String>(topic,Integer.toString(i), JSON.toJSONString(alarmMessage)), new Callback() {

                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        System.out.printf("offset = %d ,partition = %d \n", recordMetadata.offset() ,recordMetadata.partition());
                    } else {
                        logger.error("send error !" ,e);
                    }
                }
            });
        }

        producer.close();
    }

}
