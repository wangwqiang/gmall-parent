package com.atguigu.gmall.activity.service.impl;

import com.atguigu.gmall.model.activity.ActivitySku;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.activity.service.ActivitySkuService;
import com.atguigu.gmall.activity.mapper.ActivitySkuMapper;
import org.springframework.stereotype.Service;

/**
* @author wangwenqiang
* @description 针对表【activity_sku(活动参与商品)】的数据库操作Service实现
* @createDate 2022-08-31 21:40:36
*/
@Service
public class ActivitySkuServiceImpl extends ServiceImpl<ActivitySkuMapper, ActivitySku>
    implements ActivitySkuService{

}




