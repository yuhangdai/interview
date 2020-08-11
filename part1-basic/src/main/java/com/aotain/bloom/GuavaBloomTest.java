package com.aotain.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/08/10
 */
public class GuavaBloomTest {

    public static void main(String[] args) {
        BloomFilter<Integer> integerBloomFilter =
                BloomFilter.create(Funnels.integerFunnel(),1500,0.01);
        System.out.println(integerBloomFilter.mightContain(1));
        System.out.println(integerBloomFilter.mightContain(2));
        integerBloomFilter.put(1);
        integerBloomFilter.put(2);
        System.out.println(integerBloomFilter.mightContain(1));
        System.out.println(integerBloomFilter.mightContain(2));
    }
}
