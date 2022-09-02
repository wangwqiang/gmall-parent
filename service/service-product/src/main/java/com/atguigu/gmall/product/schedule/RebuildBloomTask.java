package com.atguigu.gmall.product.schedule;

import com.atguigu.gmall.common.constant.SysRedisConst;
import com.atguigu.gmall.product.bloom.BloomDataQueryService;
import com.atguigu.gmall.product.bloom.BloomOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author wangwqiang
 * date 2022/9/1
 * @version 1.0
 */
public class RebuildBloomTask {
    @Autowired
    BloomOpsService bloomOpsService;
    @Autowired
    BloomDataQueryService bloomDataQueryService;

    /**
     * 生产环境  每7天凌晨3点定时重建
     */
    @Scheduled(cron = "0 0 3 ? * 3")
    public void rebuild(){
        bloomOpsService.rebuildBloom(SysRedisConst.BLOOM_SKUID,bloomDataQueryService);
    }
}
