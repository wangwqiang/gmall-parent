package com.atguigu.gmall.item.cache;

import com.atguigu.gmall.model.to.SkuDetailTo;

public interface CacheOpsService {
    <T> T getCacheData(String cacheKey, Class<T> clazz);

    boolean bloomContains(Long skuId);

    boolean tryLock(Long skuId);

    void unlock(Long skuId);

    void saveData(String cacheKey, Object fromRpc);
}
