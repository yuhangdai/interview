package com.aotain.kafka.consumer.highlevelconsumer;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2018/11/13
 */
public class KafkaConsumer {

    /**
     * 参数， map中需要的key: zookeeper.connect,group.id
     */
    private Map<String, Object> conf = new HashMap<String, Object>();

    /**
     * 消费连接
     */
    private final ConsumerConnector consumer;

    /**
     * 多线程执行
     */
    private ExecutorService executor;

    /**
     * 构造函数
     * @param conf
     */
    public KafkaConsumer(Map<String, Object> conf) {
        this.conf = conf;

        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig());
    }

    /**
     * 消费队列数据
     * @param callback
     */
    public void customer(String topic, int threadnum, ICustomerCallback callback) {
        // TODO Auto-generated method stub
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, threadnum); // 描述读取哪个topic，需要几个线程读
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap); // 创建Streams
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic); // 每个线程对应于一个KafkaStream

        // now launch all the threads
        //
        executor = new ThreadPoolExecutor(threadnum,threadnum,0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1000),new ThreadPoolExecutor.CallerRunsPolicy());

        // now create an object to consume the messages
        //
        int threadNumber = 0;
        for (final KafkaStream<byte[], byte[]> stream : streams) {
            // 启动consumer
            executor.submit(new KafkaCustomerThread(stream, threadNumber, callback));
            // thread
            threadNumber++ ;
        }
    }

    /**
     * kafka配置
     * @return 消费者配置
     */
    private ConsumerConfig createConsumerConfig() {
        Properties props = new Properties();
        props.put("auto.offset.reset", "smallest");
        props.put("serializer.class", StringEncoder.class.getName());

        for(Map.Entry<String, Object> entry : conf.entrySet()){

            props.put(entry.getKey(), entry.getValue());
        }

        return new ConsumerConfig(props);
    }
}
