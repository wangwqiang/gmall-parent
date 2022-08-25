package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangwqiang
 * date 2022/8/25
 * @version 1.0
 */
@RestController
@RequestMapping("/admin/product")
public class SkuController {
    @Autowired
    SkuInfoService skuInfoService;

    /**
     * 获取sku分页列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result spuImageList(@PathVariable("pageNum")Long pageNum,
                               @PathVariable("pageSize")Long pageSize){
        Page<SkuInfo> page = new Page<>(pageNum,pageSize);
        Page<SkuInfo> skuInfoPage = skuInfoService.page(page);
        return Result.ok(skuInfoPage);
    }

    /**
     * 添加sku
     * @param skuInfo
     * @return
     */
    @PostMapping("/saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo){
        skuInfoService.saveSkuInfoAndValue(skuInfo);
        return Result.ok();
    }

    /**
     * sku上架
     * @param skuId
     * @return
     */
    @GetMapping("/onSale/{skuId}")
    public Result onSale(@PathVariable("skuId")Long skuId){
        skuInfoService.onSale(skuId);
        return Result.ok();
    }

    /**
     * sku下架
     * @return
     */
    @GetMapping("/cancelSale/{skuId}")
    public Result cancelSale(@PathVariable("skuId")Long skuId){
        skuInfoService.cancelSale(skuId);
        return Result.ok();
    }

}
