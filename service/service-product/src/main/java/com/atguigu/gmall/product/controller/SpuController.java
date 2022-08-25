package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.service.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wangwqiang
 * date 2022/8/24
 * @version 1.0
 */
@RestController
@RequestMapping("/admin/product")
public class SpuController {
    @Autowired
    SpuInfoService spuInfoService;
    @Autowired
    BaseTrademarkService trademarkService;
    @Autowired
    BaseSaleAttrService baseSaleAttrService;
    @Autowired
    SpuImageService spuImageService;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;

    /**
     * 获取spu分页列表
     * @param pageNum
     * @param pageSize
     * @param category3Id
     * @return
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public Result spuInfoPage(@PathVariable("pageNum")Long pageNum,
                              @PathVariable("pageSize")Long pageSize,
                              @RequestParam("category3Id")Long category3Id){
        Page<SpuInfo> page = new Page<>(pageNum,pageSize);
        spuInfoService.spuInfoPage(page,category3Id);
        return Result.ok(page);
    }

    /**
     * 获取销售属性
     * @return
     */
    @GetMapping("/baseSaleAttrList")
    public Result baseSaleAttrList(){
        List<BaseSaleAttr> saleAttrList = baseSaleAttrService.list();
        return Result.ok(saleAttrList);
    }

    /**
     * 获取品牌属性
     * @return
     */
    @GetMapping("/baseTrademark/getTrademarkList")
    public Result getTrademarkList(){
        List<BaseTrademark> trademarkList = trademarkService.list();
        return Result.ok(trademarkList);
    }

    /**
     * 添加spu
     * @return
     */
    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo){
        spuInfoService.saveSpuInfo(spuInfo);
        return Result.ok();
    }

    /**
     * 根据spuId获取图片列表
     * @param spuId
     * @return
     */
    @GetMapping("/spuImageList/{spuId}")
    public Result spuImageList(@PathVariable("spuId")Long spuId){
        List<SpuImage> imageList = spuImageService.getSpuImageList(spuId);
        return Result.ok(imageList);
    }

    /**
     * 根据spuId获取销售属性
     * @param spuId
     * @return
     */
    @GetMapping("/spuSaleAttrList/{spuId}")
    public Result spuSaleAttrList(@PathVariable("spuId")Long spuId){
        List<SpuSaleAttr> saleAttrList = spuSaleAttrService.getSpuSaleAttrList(spuId);
        return Result.ok(saleAttrList);
    }
}
