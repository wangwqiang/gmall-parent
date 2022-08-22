package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangwqiang
 * date 2022/8/22
 * @version 1.0
 */
@RestController
@RequestMapping("/admin/product")
public class AttrController {
    @Autowired
    BaseAttrInfoService attrInfoService;
    @Autowired
    BaseAttrValueService attrValueService;

    //attrInfoList/15/87/841
    @GetMapping("/attrInfoList/{c1Id}/{c2Id}/{c3Id}")
    public Result attrInfoList(@PathVariable("c1Id")Long c1Id,
                               @PathVariable("c2Id")Long c2Id,
                               @PathVariable("c3Id")Long c3Id){
        List<BaseAttrInfo> list=  attrInfoService.getAttrInfoList(c1Id,c2Id,c3Id);
        return Result.ok();
    }
}
