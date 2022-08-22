package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseCategory3;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author wangwenqiang
* @description 针对表【base_category3(三级分类表)】的数据库操作Service
* @createDate 2022-08-22 18:58:00
*/
public interface BaseCategory3Service extends IService<BaseCategory3> {

    List<BaseCategory3> getCategory3(Long c2Id);
}
