package com.atguigu.gmall.item.cache.impl;

import com.atguigu.gmall.common.constant.SysRedisConst;
import com.atguigu.gmall.common.util.Jsons;
import com.atguigu.gmall.item.cache.CacheOpsService;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author wangwqiang
 * date 2022/8/30
 * @version 1.0
 */
@Service
public class CacheOpsServiceImpl implements CacheOpsService {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    RedissonClient redissonClient;

    /**
     * 到缓存查询数据
     * @param cacheKey
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> T getCacheData(String cacheKey, Class<T> clazz) {
        String jsonStr = redisTemplate.opsForValue().get(cacheKey);
        //引入null值缓存机制
        if (SysRedisConst.NULL_VAL.equals(jsonStr)) {
            return null;
        }
        T t = Jsons.toObj(jsonStr, clazz);
        return t;
    }

    /**
     * 布隆过滤器查询数据库是否有值
     * @param skuId
     * @return
     */
    @Override
    public boolean bloomContains(Long skuId) {
        RBloomFilter<Object> filter = redissonClient.getBloomFilter(SysRedisConst.BLOOM_SKUID);
        return filter.contains(skuId);
    }

    /**
     * 为指定商品自己的加锁
     * @param skuId
     * @return
     */
    @Override
    public boolean tryLock(Long skuId) {
        //准备锁用的key
        String lockKey = SysRedisConst.LOCK_SKU_DETAIL + skuId;
        RLock lock = redissonClient.getLock(lockKey);
        //尝试加锁
        boolean tryLock = lock.tryLock();
        return tryLock;
    }

    /**
     * 把指定对象使用指定的key保存到redis
     * @param cacheKey
     * @param fromRpc
     */
    @Override
    public void saveData(String cacheKey, Object fromRpc) {
        //先判断对象是否为空
        if (fromRpc == null) {
            //null值缓存时间短一点
            redisTemplate.opsForValue()
                    .set(cacheKey,SysRedisConst.NULL_VAL,SysRedisConst.NULL_VAL_TTl, TimeUnit.SECONDS);
        }else {
            String jsonStr = Jsons.toStr(fromRpc);
            redisTemplate.opsForValue()
                    .set(cacheKey,jsonStr,SysRedisConst.SKU_DETAIL_TTL,TimeUnit.SECONDS);
        }
    }

    /**
     * 解锁
     * @param skuId
     */
    @Override
    public void unlock(Long skuId) {
        String lockKey = SysRedisConst.LOCK_SKU_DETAIL + skuId;
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }

}
