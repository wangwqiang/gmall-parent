package com.atguigu.gmall.item;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.jupiter.api.Test;

/**
 * @author wangwqiang
 * date 2022/8/29
 * @version 1.0
 */
public class BloomFilterTest {

    @Test
    public void bloomTest() {
        /**
         * Funnel<? super T> funnel,
         *          (from,into)->{
         *             into.putLong( Long.parseLong(from.toString()));
         *         }
         * int expectedInsertions,  期望插入的数量： 1w
         * double fpp：false positive probability  误判率，越低，bloom存东西hash次数越多，占位越多
         */
        //1.创建布隆过滤器
        BloomFilter<Long> bloomFilter = BloomFilter.create(Funnels.longFunnel(), 10000, 0.0001);
        //2.添加数据
        for (long i = 0; i < 20; i++) {
            bloomFilter.put(i);
        }
        //3.判断数据有没有
        System.out.println(bloomFilter.mightContain(1L));   //true
        System.out.println(bloomFilter.mightContain(15L));  //true
        System.out.println(bloomFilter.mightContain(20L));  //false
        System.out.println(bloomFilter.mightContain(30L));  //false

    }
}
