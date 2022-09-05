package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.list.SearchAttr;
import com.atguigu.gmall.model.product.SkuAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author wangwenqiang
* @description 针对表【sku_attr_value(sku平台属性值关联表)】的数据库操作Service
* @createDate 2022-08-22 20:46:18
*/
public interface SkuAttrValueService extends IService<SkuAttrValue> {

    List<SearchAttr> getSkuAttrNameAndValue(Long skuId);
}
