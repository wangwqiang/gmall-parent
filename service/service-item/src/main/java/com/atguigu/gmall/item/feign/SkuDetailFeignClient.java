package com.atguigu.gmall.item.feign;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.SkuDetailTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangwqiang
 * date 2022/8/26
 * @version 1.0
 */
@FeignClient("service-product")
@RequestMapping("/api/inner/rpc/product")
public interface SkuDetailFeignClient {

    @GetMapping("/skuDetail/{skuId}")
    Result<SkuDetailTo> getSkuDetail(@PathVariable("skuId")Long skuId);
}