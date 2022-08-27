package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.SkuImage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.service.SkuImageService;
import com.atguigu.gmall.product.mapper.SkuImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author wangwenqiang
* @description 针对表【sku_image(库存单元图片表)】的数据库操作Service实现
* @createDate 2022-08-22 20:46:18
*/
@Service
public class SkuImageServiceImpl extends ServiceImpl<SkuImageMapper, SkuImage>
    implements SkuImageService{
    @Autowired
    SkuImageMapper skuImageMapper;

    /**
     * 根据skuId查询图片列表
     * @param skuId
     * @return
     */
    @Override
    public List<SkuImage> selectImageList(Long skuId) {
       LambdaQueryWrapper<SkuImage> queryWrapper = new LambdaQueryWrapper<>();
       queryWrapper.eq(SkuImage::getSkuId,skuId);
        List<SkuImage> skuImageList = skuImageMapper.selectList(queryWrapper);
        return skuImageList;
    }
}




