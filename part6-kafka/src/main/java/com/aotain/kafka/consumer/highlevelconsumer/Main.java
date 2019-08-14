package com.aotain.kafka.consumer.highlevelconsumer;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2018/11/13
 */
public class Main {

    private static KafkaConsumer consumer;

    public static void main(String[] args) {
        Map<String,Object> conf = Maps.newHashMap();
        conf.put("zookeeper.connect","localhost:9121");
        conf.put("group.id","testGroup");
        conf.put("enable.auto.commit","false");
//        conf.put("auto.offset.reset","earliest");
        conf.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        conf.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer(conf);
        Processer jobProcesser = new Processer();
        consumer.customer("nomal-topic", 3, jobProcesser);
    }
}
