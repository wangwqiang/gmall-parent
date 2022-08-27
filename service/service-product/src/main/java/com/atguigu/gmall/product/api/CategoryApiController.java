package com.atguigu.gmall.product.api;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.CategoryTreeTo;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangwqiang
 * date 2022/8/26
 * @version 1.0
 */
@RestController
@RequestMapping("/api/inner/rpc/product")
public class CategoryApiController {
    @Autowired
    BaseCategory3Service baseCategory3Service;

    /**
     * 查询首页信息
     * @return
     */
    @GetMapping("/category/tree")
    public Result getCategoryTree(){
        List<CategoryTreeTo> list =  baseCategory3Service.getCategoryTree();
        return Result.ok(list);
    }
}
