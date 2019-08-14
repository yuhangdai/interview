package com.aotain.kafka.consumer.oneconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.List;
import java.util.Map;

/**
 * worker类。执行真正的消费逻辑并上报位移信息给ConsumerThreadHandler
 *
 * @author daiyh@aotain.com
 * @date 2018/11/13
 */
public class ConsumerWorker<K,V> implements Runnable {

    private final ConsumerRecords<K,V> records;
    private final Map<TopicPartition,OffsetAndMetadata> offsets;

    public ConsumerWorker(ConsumerRecords<K, V> records, Map<TopicPartition, OffsetAndMetadata> offsets) {
        this.records = records;
        this.offsets = offsets;
    }

    @Override
    public void run() {

        for (TopicPartition partition : records.partitions()){
            List<ConsumerRecord<K,V>> partitionRecords = records.records(partition);
            for (ConsumerRecord<K,V> record:partitionRecords){
                // 业务代码
                System.out.println(String.format("topic=%s, partition=%d, offset=%d",record.topic(),record.partition(),record.offset()));
            }
            // 上报位移信息
            long lastOffset = partitionRecords.get(partitionRecords.size()-1).offset();
            synchronized (offsets){
                if (!offsets.containsKey(partition)){
                    offsets.put(partition,new OffsetAndMetadata(lastOffset+1));
                } else {
                    long current = offsets.get(partition).offset();
                    if ( current <= lastOffset+1){
                        offsets.put(partition,new OffsetAndMetadata(lastOffset+1));
                    }
                }
            }
        }

    }
}
