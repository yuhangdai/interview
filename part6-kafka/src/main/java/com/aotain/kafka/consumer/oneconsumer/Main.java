package com.aotain.kafka.consumer.oneconsumer;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2018/11/13
 */
public class Main {
    public static void main(String[] args) {
        String brokerList = "23.106.132.161:9092";
        String groupId = "testGroup";
        String topic = "normal-topic";
        final ConsumerThreadHandler<byte[],byte[]> handler = new ConsumerThreadHandler<>(brokerList,groupId,topic);
        final int cpuCount = Runtime.getRuntime().availableProcessors();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.consumer(cpuCount);
            }
        };
        new Thread(runnable).start();

        try{
            Thread.sleep(20000L);
        } catch (InterruptedException e){

        }
        System.out.print("Starting to close the consumer...");
        handler.close();
    }
}
