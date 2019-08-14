package com.aotain.kafka.consumer.multiconsumer;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 消费线程管理类，创建多个线程类执行任务
 *
 * @author daiyh@aotain.com
 * @date 2018/11/13
 */
public class ConsumerGroup {

    private List<ConsumerRunnable> consumers;

    public ConsumerGroup(int consumerNum,String groupId,String topic,String brokerList){
        consumers = Lists.newArrayList();
        for (int i=0;i<consumerNum;i++){
            ConsumerRunnable consumerRunnable = new ConsumerRunnable(brokerList,groupId,topic);
            consumers.add(consumerRunnable);
        }
    }

    public void execute(){
        for (ConsumerRunnable task:consumers){
            new Thread(task).start();
        }
    }
}
