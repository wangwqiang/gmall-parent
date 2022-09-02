package com.atguigu.gmall.item.api;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.item.service.SkuDetailApiService;
import com.atguigu.gmall.model.to.SkuDetailTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangwqiang
 * date 2022/8/26
 * @version 1.0
 */
@RestController
@RequestMapping("/api/inner/rpc/item")
public class SkuDetailApiController {
    @Autowired
    SkuDetailApiService skuDetailApiService;

    /**
     * 查询sku详情信息
     * @param skuId
     * @return
     */
    @GetMapping("/skuDetail/{skuId}")
    public Result<SkuDetailTo> getSkuDetail(@PathVariable("skuId") Long skuId) {
        SkuDetailTo skuDetail = skuDetailApiService.getSkuDetail(skuId);
        return Result.ok(skuDetail);
    }

}
