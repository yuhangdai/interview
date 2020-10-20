package com.aotain.thread;

import com.aotain.tool.cdl.Driver;
import com.google.common.collect.Lists;
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/10/10
 */
public class ThreadTest {

    static int n = 1000;
    static CountDownLatch countDownLatch1 = new CountDownLatch(100);
    static volatile CountDownLatch countDownLatch2 = new CountDownLatch(n);
    static CountDownLatch countDownLatch3 = new CountDownLatch(100);

    public static void main(String[] args) {

        Thread threadA = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                countDownLatch1.countDown();
            }
        });



        Thread threadB = new Thread(()->{

            List<Student> drivers = new ArrayList<>(100);
            for (int i = 0; i < n; i++) {
                Student driver = new Student();
                driver.setId((long)i);
                driver.setName("name_"+i);
                drivers.add(driver);
            }


            ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 40, 300, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
            for (int i = 0; i < drivers.size(); i++) {

                final Student driver = drivers.get(i);

                executor.submit(()->{
                    try {

                        if (driver.getId() % 100 == 0){
                            List<String> unitNames = Lists.newArrayList("tttt");
                            unitNames.remove(null);
                            unitNames.remove("");
                            driver.setName(StringUtils.join(unitNames, ";"));

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            executor.shutdown();
            try {
                executor.awaitTermination(60, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int ncount = 0;
            for (int i = 0; i < drivers.size(); i++) {
                if (drivers.get(i).getName() == null || "null".equals(drivers.get(i).getName())){
                    ncount++;
                }
                System.out.println(drivers.get(i));
            }

            System.out.println(ncount);

        });

        Thread threadC = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                countDownLatch3.countDown();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        try {
            threadA.join();
            threadB.join();
            threadC.join();
//            countDownLatch1.await();
//            countDownLatch2.await();
//            countDownLatch3.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

//    public static void main(String[] args) {
//        List<String> names = Lists.newArrayList();
//        names.add("A");
//        names.add("B");
//        System.out.println(names.toString());
//    }
}
