package com.atguigu.gmall.web.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.feign.search.SearchFeignClient;
import com.atguigu.gmall.model.vo.search.SearchParamVo;
import com.atguigu.gmall.model.vo.search.SearchResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wangwqiang
 * date 2022/9/5
 * @version 1.0
 */
@Controller
public class SearchController {
    @Autowired
    SearchFeignClient searchFeignClient;

    @GetMapping("/list.html")
    public String search(SearchParamVo searchParamVo, Model model){
        Result<SearchResponseVo> result = searchFeignClient.search(searchParamVo);
        SearchResponseVo data = result.getData();
        //1.检索页面条件
        model.addAttribute("searchParam",data.getSearchParam());
        //2.品牌面包屑位置显示
        model.addAttribute("trademarkParam",data.getTrademarkParam());
        //3.属性面包屑位置显示
        model.addAttribute("propsParamList",data.getPropsParamList());
        //4.品牌显示
        model.addAttribute("trademarkList",data.getTrademarkList());
        //5.属性显示
        model.addAttribute("attrsList",data.getAttrsList());
        //6.排序
        model.addAttribute("orderMap",data.getOrderMap());
        //7.所有商品
        model.addAttribute("goodsList",data.getGoodsList());
        //8.分页
        model.addAttribute("pageNo",data.getPageNo());
        model.addAttribute("totalPages",data.getTotalPages());
        //9.url
        model.addAttribute("urlParam",data.getUrlParam());
        return "list/index";
    }
    
}
