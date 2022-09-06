package com.atguigu.gmall.product.api;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wangwqiang
 * date 2022/8/26
 * @version 1.0
 */
@RestController
@RequestMapping("/api/inner/rpc/product")
public class SkuDetailController {
    @Autowired
    SkuInfoService skuInfoService;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;
    @Autowired
    BaseCategory3Service baseCategory3Service;
    /**
     * 查询sku详情信息
     * @param skuId
     * @return
     */
//    @GetMapping("/skuDetail/{skuId}")
//    public Result<SkuDetailTo> getSkuDetail(@PathVariable("skuId")Long skuId){
//        SkuDetailTo skuDetailTo = skuInfoService.getSkuDetail(skuId);
//        return Result.ok(skuDetailTo);
//    }

    /**
     * 1.查询skuInfo基本信息
     *
     * @param skuId
     * @return
     */
    @GetMapping("/skuDetail/info/{skuId}")
    public Result<SkuInfo> getSkuInfo(@PathVariable Long skuId) {
        SkuInfo skuInfo = skuInfoService.getDetailSkuInfo(skuId);
        return Result.ok(skuInfo);
    }

    /**
     * 2.查询图片信息
     *
     * @param skuId
     * @return
     */
    @GetMapping("/skuDetail/images/{skuId}")
    public Result<List<SkuImage>> getSkuImages(@PathVariable Long skuId) {
        List<SkuImage> skuImages = skuInfoService.getDetailSkuImages(skuId);
        return Result.ok(skuImages);
    }

    /**
     * 3.查询商品实时价格
     *
     * @param skuId
     * @return
     */
    @GetMapping("/skuDetail/price/{skuId}")
    public Result<BigDecimal> getSku1010Price(@PathVariable Long skuId) {
        BigDecimal price = skuInfoService.getDetailSku1010Price(skuId);
        return Result.ok(price);
    }

    /**
     * 查询sku对应的spu定义的所有销售属性名和值。并且标记出当前sku是哪个
     *
     * @param skuId
     * @param spuId
     * @return
     */
    @GetMapping("/skuDetail/spuSaleAttr/{skuId}/{spuId}")
    public Result<List<SpuSaleAttr>> getSkuSaleAttrValues(@PathVariable("skuId") Long skuId,
                                                          @PathVariable("spuId") Long spuId) {
        List<SpuSaleAttr> spuSaleAttrAndValue =
                spuSaleAttrService.getSpuSaleAttrAndValueMarkSku(skuId, spuId);
        return Result.ok(spuSaleAttrAndValue);
    }

    /**
     * 5.查询sku组合
     *
     * @param spuId
     * @return
     */
    @GetMapping("/skuDetail/skuValueJson/{spuId}")
    public Result<String> getSkuValueJson(@PathVariable Long spuId) {
        String valueJson = spuSaleAttrService.getDetailSkuValueJson(spuId);
        return Result.ok(valueJson);
    }

    /**
     * 6.查询分类
     *
     * @param c3Id
     * @return
     */
    @GetMapping("/skuDetail/category/{c3Id}")
    public Result<CategoryViewTo> getCategoryView(@PathVariable("c3Id") Long c3Id) {
        CategoryViewTo categoryViewTo = baseCategory3Service.getCategoryView(c3Id);
        return Result.ok(categoryViewTo);
    }
}
