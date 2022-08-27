package com.atguigu.gmall.activity.controller;

import com.atguigu.gmall.activity.service.CouponInfoService;
import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.activity.CouponInfo;
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
public class CouponController {
    @Autowired
    CouponInfoService couponInfoService;

    /**
     * 查询优惠券列表分页信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/couponInfo/{pageNum}/{pageSize}")
    public Result couponInfo(@PathVariable("pageNum")Long pageNum,
                             @PathVariable("pageSize")Long pageSize){
        Page<CouponInfo> page = new Page<>(pageNum,pageSize);
        Page<CouponInfo> couponInfoPage = couponInfoService.page(page);
        return Result.ok(couponInfoPage);
    }
}
