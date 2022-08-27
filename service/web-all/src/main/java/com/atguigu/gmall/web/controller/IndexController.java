package com.atguigu.gmall.web.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.CategoryTreeTo;
import com.atguigu.gmall.web.feign.CategoryFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author wangwqiang
 * date 2022/8/26
 * @version 1.0
 */
@Controller
public class IndexController {
    @Autowired
    CategoryFeignClient categoryFeignClient;

    /**
     * 查询首页数据跳转到首页
     * @param model
     * @return
     */
    @GetMapping({"/index","/"})
    public String index(Model model){
        Result<List<CategoryTreeTo>> categoryTree = categoryFeignClient.getCategoryTree();
        if (categoryTree.isOk()) {
            Object data = categoryTree.getData();
            model.addAttribute("list",data);
        }
        return "index/index";
    }
}
