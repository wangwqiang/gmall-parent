package com.atguigu.gmall.item.service.impl;

import com.atguigu.gmall.common.constant.SysRedisConst;
import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.common.util.Jsons;
import com.atguigu.gmall.item.cache.CacheOpsService;
import com.atguigu.gmall.item.feign.SkuDetailFeignClient;
import com.atguigu.gmall.item.service.SkuDetailApiService;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wangwqiang
 * date 2022/8/27
 * @version 1.0
 */

@Slf4j
@Service
public class SkuDetailApiServiceImpl implements SkuDetailApiService {
    @Autowired
    SkuDetailFeignClient skuDetailFeignClient;
    @Autowired
    ThreadPoolExecutor executor;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    CacheOpsService cacheOpsService;

    /**
     * 查询sku详情信息
     *
     * @param skuId
     * @return
     */
    public SkuDetailTo getSkuDetailFromRpc(Long skuId) {
        SkuDetailTo detailTo = new SkuDetailTo();

        //1.查询基本信息
        CompletableFuture<SkuInfo> skuInfoFuture = CompletableFuture.supplyAsync(() -> {
            Result<SkuInfo> result = skuDetailFeignClient.getSkuInfo(skuId);
            SkuInfo skuInfo = result.getData();
            detailTo.setSkuInfo(skuInfo);
            return skuInfo;
        }, executor);

        //2.查询图片信息
        CompletableFuture<Void> imageFuture = skuInfoFuture.thenAcceptAsync(skuInfo -> {
            if (skuInfo != null) {
                Result<List<SkuImage>> skuImages = skuDetailFeignClient.getSkuImages(skuId);
                skuInfo.setSkuImageList(skuImages.getData());
            }
        }, executor);


        //3.查询商品实时价格
        CompletableFuture<Void> priceFuture = CompletableFuture.runAsync(() -> {
            Result<BigDecimal> sku1010Price = skuDetailFeignClient.getSku1010Price(skuId);
            detailTo.setPrice(sku1010Price.getData());
        }, executor);

        //4.查询销售属性名
        CompletableFuture<Void> saleAttrFuture = skuInfoFuture.thenAcceptAsync(skuInfo -> {
            if (skuInfo != null) {
                Result<List<SpuSaleAttr>> skuSaleAttrValues = skuDetailFeignClient.getSkuSaleAttrValues(skuId, skuInfo.getSpuId());
                detailTo.setSpuSaleAttrList(skuSaleAttrValues.getData());
            }
        }, executor);


        //5.查询sku组合
        CompletableFuture<Void> valueJsonFuture = skuInfoFuture.thenAcceptAsync(skuInfo -> {
            if (skuInfo != null) {
                Result<String> skuValueJson = skuDetailFeignClient.getSkuValueJson(skuInfo.getSpuId());
                detailTo.setValuesSkuJson(skuValueJson.getData());
            }
        }, executor);

        //6.查询分类
        CompletableFuture<Void> categoryFuture = skuInfoFuture.thenAcceptAsync(skuInfo -> {
            if (skuInfo != null) {
                Result<CategoryViewTo> categoryView = skuDetailFeignClient.getCategoryView(skuInfo.getCategory3Id());
                detailTo.setCategoryViewTo(categoryView.getData());
            }
        }, executor);

        CompletableFuture
                .allOf(imageFuture, priceFuture, saleAttrFuture, valueJsonFuture, categoryFuture)
                .join();

        return detailTo;
    }

    @Override
    public SkuDetailTo getSkuDetail(Long skuId) {
        String cacheKey = SysRedisConst.SKU_INFO_PREFIX + skuId;

        //1.查询缓存
        SkuDetailTo cacheData = cacheOpsService.getCacheData(cacheKey, SkuDetailTo.class);
        //2.判断缓存中有没有数据
        if (cacheData == null) {
            //2.1 缓存中没有
            //3.先询问布隆过滤器,数据库是否有数据
            boolean contain = cacheOpsService.bloomContains(skuId);
            //4.判断布隆过滤器有没有
            if (!contain) {
                //4.1 没有(一定没有)
                log.info("{} 号商品不存在", skuId);
                return null;
            }
            //4.2 布隆说有(有可能有)，需要回源
            //5.为商品加自己的锁
            boolean lock = cacheOpsService.tryLock(skuId);
            if (lock) {
                //5.1获取锁成功，远程查询查询数据
                SkuDetailTo fromRpc = getSkuDetailFromRpc(skuId);
                //6.将查到的数据放入缓存
                cacheOpsService.saveData(cacheKey, fromRpc);
                //7.解锁
                cacheOpsService.unlock(skuId);
                //8.将查询到的数据返回
                return fromRpc;

            }
            //5.2获取锁失败，等待，之后直接到缓存中查询
            try {
                Thread.sleep(1000);
                return cacheOpsService.getCacheData(cacheKey, SkuDetailTo.class);
            } catch (InterruptedException e) {

            }
        }
        //2.2 缓存中有，直接返回
        return cacheData;
    }


    public SkuDetailTo xxxgetSkuDetail(Long skuId) {
        //1.先查询缓存中是否存在
        String jsonStr = redisTemplate.opsForValue().get("sku:info:" + skuId);
        //说明以前查过，数据中没有记录，为避免再次回源，缓存一个占位符
        if ("x".equals(jsonStr)) {
            return null;
        }
        if (StringUtils.isEmpty(jsonStr)) {
            //2.redis中不存在，
            // 回源，防止缓存穿透
            SkuDetailTo fromRpc = getSkuDetailFromRpc(skuId);
            //放入缓存
            String cacheJson = "x";
            if (fromRpc != null) {
                cacheJson = Jsons.toStr(fromRpc);
                redisTemplate.opsForValue().set("sku:info:" + skuId, cacheJson, 7, TimeUnit.DAYS);
            } else {
                redisTemplate.opsForValue().set("sku:info:" + skuId, cacheJson, 30, TimeUnit.MINUTES);

            }
            return fromRpc;
        }
        //3.缓存中存在数据，将字符串转为对象返回
        SkuDetailTo skuDetailTo = Jsons.toObj(jsonStr, SkuDetailTo.class);
        return skuDetailTo;
    }


}
