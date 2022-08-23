package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.product.service.BaseCategory1Service;
import com.atguigu.gmall.product.service.BaseCategory2Service;
import com.atguigu.gmall.product.service.BaseCategory3Service;
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
public class BaseCategoryController {
    @Autowired
    BaseCategory1Service category1Service;
    @Autowired
    BaseCategory2Service category2Service;
    @Autowired
    BaseCategory3Service category3Service;

    /**
     * 查询一级分类列表
     * @return
     */
    @GetMapping("/getCategory1")
    public Result getCategory1(){
        List<BaseCategory1> baseCategory1List = category1Service.list();
        return Result.ok(baseCategory1List);
    }

    /**
     * 查询二级分类列表
     * @return
     */
    @GetMapping("/getCategory2/{c1Id}")
    public Result getCategory2(@PathVariable("c1Id")Long c1Id){
        List<BaseCategory2> baseCategory2List = category2Service.getCategory2(c1Id);
        return Result.ok(baseCategory2List);
    }

    /**
     * 查询三级分类列表
     * @return
     */
    @GetMapping("/getCategory3/{c2Id}")
    public Result getCategory3(@PathVariable("c2Id")Long c2Id){
        List<BaseCategory3> baseCategory3List = category3Service.getCategory3(c2Id);
        return Result.ok(baseCategory3List);
    }
}
