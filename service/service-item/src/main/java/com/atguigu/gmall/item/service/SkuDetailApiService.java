package com.atguigu.gmall.item.service;

import com.atguigu.gmall.model.to.SkuDetailTo;

public interface SkuDetailApiService {
    SkuDetailTo getSkuDetail(Long skuId);
}
