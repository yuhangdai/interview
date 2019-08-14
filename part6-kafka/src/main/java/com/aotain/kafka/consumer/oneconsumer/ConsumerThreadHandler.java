package com.aotain.kafka.consumer.oneconsumer;

import com.google.common.collect.Maps;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * consumer多线程管理类，用于创建线程池以及为每个线程分配消息集合。
 * consumer位移提交也由此类完成
 * @author daiyh@aotain.com
 * @date 2018/11/13
 */
public class ConsumerThreadHandler<K,V> {

     /** 全局唯一kafka消费者用于消费kafka消息 **/
    private final KafkaConsumer<K,V> consumer;

    private ExecutorService executorService;

    private final Map<TopicPartition,OffsetAndMetadata> offsets = Maps.newHashMap();

    public ConsumerThreadHandler(String brokerList,String groupId,String topic) {
        // 构造properties
        Properties properties = new Properties();
        properties.put("bootstrap.servers",brokerList);
        properties.put("group.id",groupId);
        properties.put("enable.auto.commit","false");
        properties.put("auto.offset.reset","earliest");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        this.consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topic), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                // 提交位移
                consumer.commitSync(offsets);
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                offsets.clear();
            }
        });
    }

    /**
     * 消息处理方法
     * @param threadNum
     */
    public void consumer(int threadNum){
        executorService = new ThreadPoolExecutor(
                threadNum,threadNum,0L, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(1000),new ThreadPoolExecutor.CallerRunsPolicy());
        try{
            while(true){
                ConsumerRecords<K,V> records = consumer.poll(1000);
                if (!records.isEmpty()){
                    executorService.submit(new ConsumerWorker<>(records,offsets));
                }
                commitOffsets();
            }
        } catch (WakeupException e){

        } finally {
            commitOffsets();
            consumer.close();
        }

    }

    private void commitOffsets() {
        Map<TopicPartition,OffsetAndMetadata> unmodifiedMap;
        synchronized (offsets){
            if (offsets.isEmpty()){
                return;
            }
            unmodifiedMap = Collections.unmodifiableMap(new HashMap<>(offsets));
            offsets.clear();
        }
        consumer.commitSync(unmodifiedMap);
    }

    public void close(){
        consumer.wakeup();
        executorService.shutdown();
    }
}
