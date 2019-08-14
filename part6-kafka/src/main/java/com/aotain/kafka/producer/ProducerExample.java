package com.aotain.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Demo class
 *
 * @author HP
 * @date 2018/03/13
 */
public class ProducerExample {

    private static Logger logger = LoggerFactory.getLogger(ProducerExample.class);

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
        props.put("bootstrap.servers", "23.106.132.161:9092");
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
        String topic = "normal-topic";
        int partition = 0;
        long record = 1;
        // 发送消息
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, String>(topic, partition,Integer.toString(i), Integer.toString(i)), new Callback() {

                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        System.out.printf("offset = %d ,partition = %d \n", recordMetadata.offset() ,recordMetadata.partition());
                    } else {
                        logger.error("send error !" ,e);
                    }
                }
            });
        }

        TimeUnit.SECONDS.sleep(3);
        producer.close();
    }

}
