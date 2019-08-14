package com.aotain.kafka.consumer.multiconsumer;

/**
 * 测试主方法类
 *
 * @author daiyh@aotain.com
 * @date 2018/11/13
 */
public class ConsumerMain {

    public static void main(String[] args) {
        String brokerList = "23.106.132.161:9092";
        String groupId = "testGroup";
        String topic = "normal-topic";
        int consumerNum = 3;
        ConsumerGroup consumerGroup = new ConsumerGroup(consumerNum,groupId,topic,brokerList);
        consumerGroup.execute();
    }
}
