package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.SkuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author wangwenqiang
* @description 针对表【sku_info(库存单元表)】的数据库操作Mapper
* @createDate 2022-08-22 20:46:18
* @Entity com.atguigu.gmall.product.domain.SkuInfo
*/
public interface SkuInfoMapper extends BaseMapper<SkuInfo> {

    void updateIsSale(@Param("skuId") Long sku_Id, @Param("isSale") int isSale);
}




