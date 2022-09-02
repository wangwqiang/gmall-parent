package com.atguigu.gmall.activity.service.impl;

import com.atguigu.gmall.model.activity.ActivityInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.activity.service.ActivityInfoService;
import com.atguigu.gmall.activity.mapper.ActivityInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author wangwenqiang
* @description 针对表【activity_info(活动表)】的数据库操作Service实现
* @createDate 2022-08-26 00:31:47
*/
@Service
public class ActivityInfoServiceImpl extends ServiceImpl<ActivityInfoMapper, ActivityInfo>
    implements ActivityInfoService{

}




