package com.atguigu.gmall.product.init;

import com.atguigu.gmall.common.constant.SysRedisConst;
import com.atguigu.gmall.product.service.SkuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author wangwqiang
 * date 2022/8/30
 * @version 1.0
 */
@Service
@Slf4j
public class SkuIdBloomInitService {
    @Autowired
    SkuInfoService skuInfoService;
    @Autowired
    RedissonClient redissonClient;

    /**
     * 项目已启动就运行
     */
    @PostConstruct
    public void initSkuIdBloom(){
        log.info("布隆过滤器正在初始化中...");
        //1.查询出所有商品的skuId
        List<Long> skuIds = skuInfoService.getAllSkuId();
        //2.将所有的skuId初始化到布隆过滤器中
        RBloomFilter<Object> filter = redissonClient.getBloomFilter(SysRedisConst.BLOOM_SKUID);
        //3.初始化布隆过滤器
        //long expectedInsertions, 期望插入的数据量
        //double falseProbability  误判率
        boolean exists = filter.isExists();
        if (!exists) {
            //如果布隆过滤器没有初始化过，就尝试初始化
            filter.tryInit(5000000,0.00001);
        }
        //4.将所有商品的id添加到过滤器中
        for (Long skuId : skuIds) {
            filter.add(skuId);
        }
        log.info("布隆过滤器初始化完成，一共添加了 {} 条数据",skuIds.size());
    }
}
