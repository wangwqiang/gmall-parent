package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
* @author wangwenqiang
* @description 针对表【sku_info(库存单元表)】的数据库操作Service
* @createDate 2022-08-22 20:46:18
*/
public interface SkuInfoService extends IService<SkuInfo> {

    void onSale(Long skuId);

    void cancelSale(Long skuId);

    void saveSkuInfoAndValue(SkuInfo skuInfo);

    SkuDetailTo getSkuDetail(Long skuId);

    BigDecimal get1010Price(Long skuId);

    SkuInfo getDetailSkuInfo(Long skuId);

    List<SkuImage> getDetailSkuImages(Long skuId);

    BigDecimal getDetailSku1010Price(Long skuId);
}