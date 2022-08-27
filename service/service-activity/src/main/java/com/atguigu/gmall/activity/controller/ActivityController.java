package com.atguigu.gmall.activity.controller;

import com.atguigu.gmall.activity.service.ActivityInfoService;
import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.activity.ActivityInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
@RequestMapping("/admin/activity")
public class ActivityController {
    @Autowired
    ActivityInfoService activityInfoService;

    /**
     * 查询活动列表分页信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/activityInfo/{pageNum}/{pageSize}")
    public Result activityInfo(@PathVariable("pageNum")Long pageNum,
                               @PathVariable("pageSize")Long pageSize){
        Page<ActivityInfo> page = new Page<>(pageNum,pageSize);
        Page<ActivityInfo> activityInfoPage = activityInfoService.page(page);
        return Result.ok(activityInfoPage);
    }
}
