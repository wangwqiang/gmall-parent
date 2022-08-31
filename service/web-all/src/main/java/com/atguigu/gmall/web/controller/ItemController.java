package com.atguigu.gmall.web.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.web.feign.SkuDetailFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wangwqiang
 * date 2022/8/26
 * @version 1.0
 */
@Controller
public class ItemController {
    @Autowired
    SkuDetailFeignClient skuDetailFeignClient;

    /**
     * sku详情信息
     * @param skuId
     * @param model
     * @return
     */
    @GetMapping("/{skuId}.html")
    public String getSkuDetail(@PathVariable("skuId")Long skuId, Model model){
        Result<SkuDetailTo> skuDetail = skuDetailFeignClient.getSkuDetail(skuId);
        if (skuDetail.isOk()) {
            SkuDetailTo data = skuDetail.getData();
            if (data == null || data.getSkuInfo() == null) {
                return "item/404";
            }
            model.addAttribute("skuInfo",data.getSkuInfo());
            model.addAttribute("categoryView",data.getCategoryViewTo());
            model.addAttribute("price",data.getPrice());
            model.addAttribute("spuSaleAttrList",data.getSpuSaleAttrList());
            model.addAttribute("valuesSkuJson",data.getValuesSkuJson());
        }
        return "item/index";
    }
}
