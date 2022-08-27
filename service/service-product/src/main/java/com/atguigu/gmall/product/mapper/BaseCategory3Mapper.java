package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.model.to.CategoryTreeTo;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author wangwenqiang
* @description 针对表【base_category3(三级分类表)】的数据库操作Mapper
* @createDate 2022-08-22 18:58:00
* @Entity com.atguigu.gmall.product.domain.BaseCategory3
*/
public interface BaseCategory3Mapper extends BaseMapper<BaseCategory3> {

    List<CategoryTreeTo> getCategoryTree();

    CategoryViewTo getCategory(Long category3Id);
}




