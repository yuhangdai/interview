package com.aotain.bloom;

import java.util.BitSet;

/**
 * 自定义bloom过滤器
 *
 * @author bang
 * @date 2020/08/10
 */
public class MyBloomFilter {

    /**
     * 位数组大小
     */
    private static final int DEFAULT_SIZE = 2 << 24;

    /**
     * bloom hash函数参数
     */
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

    private BitSet bitSet = new BitSet(DEFAULT_SIZE);

    private SimpleHash[] func = new SimpleHash[SEEDS.length];

    /**
     * 初始化多个包含 hash 函数的类的数组，每个类中的 hash 函数都不一样
     */
    public MyBloomFilter() {
        // 初始化多个不同的 Hash 函数
        for (int i = 0; i < SEEDS.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    /**
     * 添加元素到位数组中
     * @param value
     */
    public void add(Object value) {
        for (SimpleHash f : func){
            bitSet.set(f.hash(value),true);
        }
    }

    /**
     * 判断指定元素是否存在于位数组
     */
    public boolean contains(Object value) {
        boolean ret = true;
        for (SimpleHash f :func){
            ret = ret && bitSet.get(f.hash(value));
        }
        return ret;
    }

    public static void main(String[] args) {
//        String value1 = "https://javaguide.cn/";
//        String value2 = "https://github.com/Snailclimb";
//        MyBloomFilter filter = new MyBloomFilter();
//        System.out.println(filter.contains(value1));
//        System.out.println(filter.contains(value2));
//        filter.add(value1);
//        filter.add(value2);
//        System.out.println(filter.contains(value1));
//        System.out.println(filter.contains(value2));

        Integer value1 = 13423;
        Integer value2 = 22131;
        MyBloomFilter filter = new MyBloomFilter();
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
        filter.add(value1);
        filter.add(value2);
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
    }

    static class SimpleHash{
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        /**
         * 计算 hash 值
         */
        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }
    }
}
