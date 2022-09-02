package com.atguigu.gmall.feign.product;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.CategoryViewTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wangwqiang
 * date 2022/8/26
 * @version 1.0
 */
@FeignClient("service-product")
@RequestMapping("/api/inner/rpc/product")
public interface SkuProductDetailFeignClient {

//    @GetMapping("/skuDetail/{skuId}")
//    Result<SkuDetailTo> getSkuDetail(@PathVariable("skuId")Long skuId);

    /**
     * 1.查询基skuInfo本信息
     *
     * @param skuId
     * @return
     */
    @GetMapping("/skuDetail/info/{skuId}")
    Result<SkuInfo> getSkuInfo(@PathVariable Long skuId);

    /**
     * 2.查询图片信息
     *
     * @param skuId
     * @return
     */
    @GetMapping("/skuDetail/images/{skuId}")
    Result<List<SkuImage>> getSkuImages(@PathVariable Long skuId);

    /**
     * 3.查询商品实时价格
     *
     * @param skuId
     * @return
     */
    @GetMapping("/skuDetail/price/{skuId}")
    Result<BigDecimal> getSku1010Price(@PathVariable Long skuId);

    /**
     * 查询sku对应的spu定义的所有销售属性名和值。并且标记出当前sku是哪个
     * @param skuId
     * @param spuId
     * @return
     */
    @GetMapping("/skuDetail/spuSaleAttr/{skuId}/{spuId}")
    Result< List<SpuSaleAttr>> getSkuSaleAttrValues(@PathVariable("skuId") Long skuId,
                                                    @PathVariable("spuId") Long spuId);

    /**
     * 5.查询sku组合
     * @param spuId
     * @return
     */
    @GetMapping("/skuDetail/skuValueJson/{spuId}")
    Result<String> getSkuValueJson(@PathVariable Long spuId);

    /**
     * 6.查询分类
     * @param c3Id
     * @return
     */
    @GetMapping("/skuDetail/category/{c3Id}")
    Result<CategoryViewTo> getCategoryView(@PathVariable("c3Id")Long c3Id);
}
