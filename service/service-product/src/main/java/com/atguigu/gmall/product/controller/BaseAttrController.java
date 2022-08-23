package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wangwqiang
 * date 2022/8/22
 * @version 1.0
 */
@RestController
@RequestMapping("/admin/product")
public class BaseAttrController {
    @Autowired
    BaseAttrInfoService attrInfoService;
    @Autowired
    BaseAttrValueService attrValueService;
    /**
     * 查询平台属性列表
     * @param c1Id 一级分类id
     * @param c2Id 二级分类id
     * @param c3Id 三级分类id
     * @return
     */
    @GetMapping("/attrInfoList/{c1Id}/{c2Id}/{c3Id}")
    public Result attrInfoList(@PathVariable("c1Id")Long c1Id,
                               @PathVariable("c2Id")Long c2Id,
                               @PathVariable("c3Id")Long c3Id){
        List<BaseAttrInfo> list=  attrInfoService.getAttrInfoAndValueByCategoryId(c1Id,c2Id,c3Id);
        return Result.ok(list);
    }

    /**
     * 保存、修改属性信息
     * @param baseAttrInfo
     * @return
     */
    @PostMapping("/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        attrInfoService.saveAttrInfo(baseAttrInfo);
        return Result.ok();
    }

    /**
     * 根据属性id查询属性值
     * @param attrId
     * @return
     */
    @GetMapping("/getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable("attrId")Long attrId){
        List<BaseAttrValue> baseAttrValue = attrValueService.getAttrValueList(attrId);
        return Result.ok(baseAttrValue);
    }
}
