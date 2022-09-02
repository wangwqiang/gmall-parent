package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.constant.SysRedisConst;
import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.bloom.BloomDataQueryService;
import com.atguigu.gmall.product.bloom.BloomOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangwqiang
 * date 2022/9/1
 * @version 1.0
 */
@RequestMapping("/admin/product")
@RestController
public class BloomOpsController {
    @Autowired
    BloomOpsService bloomOpsService;
    @Autowired
    BloomDataQueryService dataQueryService;

    @GetMapping("/rebuild/now")
    public Result rebuildBloom(){
        String bloomName = SysRedisConst.BLOOM_SKUID;
        bloomOpsService.rebuildBloom(bloomName,dataQueryService);
        return Result.ok();
    }
}
