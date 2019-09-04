package com.aotain.demo.consumer;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Demo class
 *
 * @author bang
 * @date 2019/08/23
 */
public class Customers {

    private static final Logger logger = LoggerFactory.getLogger(Customers.class);

    private ICustom custom;

    private String brokerList;
    private String topic;
    private String groupId;
    private int numConsumers;

    private List<RunnableCustom> runnableCustomList = Lists.newArrayList();
    private ExecutorService executor;

    public Customers(String brokerList,String topic,String groupId,int numConsumers,ICustom custom){
        this.brokerList = brokerList;
        this.topic = topic;
        this.groupId = groupId;
        this.numConsumers = numConsumers;
        this.custom = custom;
    }

    public void dealMessage(){
        executor = Executors.newFixedThreadPool(numConsumers);
        for (int i=0;i<numConsumers;i++){
            RunnableCustom runnableCustom = new RunnableCustom(brokerList,groupId,topic,custom);
            runnableCustomList.add(runnableCustom);
            executor.submit(runnableCustom);
        }
    }

    public void shutdown(){
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                for (RunnableCustom consumer : runnableCustomList) {
                    consumer.shutdown();
                }
                executor.shutdown();
                try {
                    executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    logger.info("shutdown customers fail ");
                }
            }
        });
    }

}
