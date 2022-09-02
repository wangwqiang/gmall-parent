package com.atguigu.gmall.product.bloom.impl;

import com.atguigu.gmall.product.bloom.BloomDataQueryService;
import com.atguigu.gmall.product.bloom.BloomOpsService;
import com.atguigu.gmall.product.service.SkuInfoService;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangwqiang
 * date 2022/9/1
 * @version 1.0
 */
@Service
public class BloomOpsServiceImpl implements BloomOpsService {
    @Autowired
    RedissonClient  redissonClient;
    @Autowired
    SkuInfoService skuInfoService;

    /**
     * 重建指定布隆过滤器
     * @param bloomName
     */
    @Override
    public void rebuildBloom(String bloomName, BloomDataQueryService dataQueryService) {
        RBloomFilter<Object> oldBloomFilter = redissonClient.getBloomFilter(bloomName);
        //1.先准备一个新的布隆过滤器，初始化好
        String newBloomFilter = bloomName + "_new";
        RBloomFilter<Object> bloomFilter = (RBloomFilter<Object>) redissonClient.getBloomFilter(newBloomFilter);
        //2.拿到所有商品的id
        List<Long> skuIds = skuInfoService.getAllSkuId();
//        List skuIds = dataQueryService.queryData();   //动态决定
        //3.初始化布隆过滤器
        bloomFilter.tryInit(5000000,0.00001);
        for (Object skuId : skuIds) {
            bloomFilter.add(skuId);
        }
        //4.新的布隆过滤器准备就绪
        //5.定义临时过滤器，交换两个新旧过滤器
        oldBloomFilter.rename("bloom"); //oldBloomFilter下线
        bloomFilter.rename(bloomName);  //新的过滤器上线
        //6.删除旧的和临时过滤器
        oldBloomFilter.deleteAsync();
        redissonClient.getBloomFilter("bloom").deleteAsync();

    }
}
