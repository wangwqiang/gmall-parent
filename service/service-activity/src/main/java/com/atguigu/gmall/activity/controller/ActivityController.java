package com.atguigu.gmall.activity.controller;

import com.atguigu.gmall.activity.service.ActivityInfoService;
import com.atguigu.gmall.activity.service.ActivityRuleService;
import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.activity.ActivityInfo;
import com.atguigu.gmall.model.activity.ActivityRule;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    @Autowired
    ActivityRuleService activityRuleService;

    /**
     * 查询活动列表分页信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/activityInfo/{pageNum}/{pageSize}")
    public Result activityInfo(@PathVariable("pageNum") Long pageNum,
                               @PathVariable("pageSize") Long pageSize) {
        Page<ActivityInfo> page = new Page<>(pageNum, pageSize);
        Page<ActivityInfo> activityInfoPage = activityInfoService.page(page);
        return Result.ok(activityInfoPage);
    }

    /**
     * 添加活动
     * @param activityInfo
     * @return
     */
    @PostMapping("/activityInfo/save")
    public Result save(@RequestBody ActivityInfo activityInfo) {
        activityInfo.setCreateTime(new Date());
        activityInfoService.save(activityInfo);
        return Result.ok();
    }

    /**
     * 根据id查询活动信息
     * @param id
     * @return
     */
    @GetMapping("/activityInfo/get/{id}")
    public Result getActivityInfo(@PathVariable("id") Long id) {
        ActivityInfo activityInfo = activityInfoService.getById(id);
        return Result.ok(activityInfo);
    }

    /**
     * 根据id修改活动信息
     * @param activityInfo
     * @return
     */
    @PutMapping("/activityInfo/update")
    public Result update(@RequestBody ActivityInfo activityInfo) {
        activityInfoService.updateById(activityInfo);
        return Result.ok();
    }

    /**
     * 根据id删除活动信息
     * @param id
     * @return
     */
    @DeleteMapping("/activityInfo/remove/{id}")
    public Result remove(@PathVariable("id")Long id){
        activityInfoService.removeById(id);
        return Result.ok();
    }

    /**
     * 批量删除活动信息
     * @param ids
     * @return
     */
    @DeleteMapping("/activityInfo/batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids){
        activityInfoService.removeByIds(ids);
        return Result.ok();
    }

    /**
     * 根据活动id查询活动规则
     * @param activityId
     * @return
     */
    @GetMapping("/activityInfo/findActivityRuleList/{activityId}")
    public Result findActivityRuleList(@PathVariable("activityId")Long activityId){
        ActivityRule activityRule = activityRuleService.getById(activityId);
        return Result.ok(activityRule);
    }

}
