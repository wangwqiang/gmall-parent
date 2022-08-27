package com.atguigu.gmall.item.service.impl;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.item.feign.SkuDetailFeignClient;
import com.atguigu.gmall.item.service.SkuDetailApiService;
import com.atguigu.gmall.model.to.SkuDetailTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangwqiang
 * date 2022/8/27
 * @version 1.0
 */
@Service
public class SkuDetailApiServiceImpl implements SkuDetailApiService {
    @Autowired
    SkuDetailFeignClient skuDetailFeignClient;

    /**
     * 查询sku详情信息
     * @param skuId
     * @return
     */
    @Override
    public SkuDetailTo getSkuDetail(Long skuId) {
        Result<SkuDetailTo> skuDetail = skuDetailFeignClient.getSkuDetail(skuId);
        return skuDetail.getData();
    }
}
