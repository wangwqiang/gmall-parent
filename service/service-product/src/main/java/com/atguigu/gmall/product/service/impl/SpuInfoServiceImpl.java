package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.atguigu.gmall.product.mapper.SpuImageMapper;
import com.atguigu.gmall.product.mapper.SpuSaleAttrMapper;
import com.atguigu.gmall.product.mapper.SpuSaleAttrValueMapper;
import com.atguigu.gmall.product.service.SpuImageService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.atguigu.gmall.product.service.SpuSaleAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.service.SpuInfoService;
import com.atguigu.gmall.product.mapper.SpuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author wangwenqiang
 * @description 针对表【spu_info(商品表)】的数据库操作Service实现
 * @createDate 2022-08-22 20:46:18
 */
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo>
        implements SpuInfoService {
    @Autowired
    SpuInfoMapper spuInfoMapper;
    @Autowired
    SpuImageService spuImageService;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;
    @Autowired
    SpuSaleAttrValueService spuSaleAttrValueService;

    /**
     * 获取spu分页列表
     * @param page
     * @param category3Id
     */
    @Override
    public void spuInfoPage(Page<SpuInfo> page, Long category3Id) {
        LambdaQueryWrapper<SpuInfo> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SpuInfo::getCategory3Id, category3Id);
        spuInfoMapper.selectPage(page, queryWrapper);
    }

    /**
     * 添加spu
     * @param spuInfo
     */
    @Override
    @Transactional
    public void saveSpuInfo(SpuInfo spuInfo) {
        //添加spuInfo
        spuInfoMapper.insert(spuInfo);
        //添加spuImage
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        for (SpuImage spuImage : spuImageList) {
            if (spuImage != null) {
                spuImage.setSpuId(spuInfo.getId());
            }
        }
        spuImageService.saveBatch(spuImageList);
        //添加spuSaleAttr
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
            if (spuSaleAttr != null) {
                spuSaleAttr.setSpuId(spuInfo.getId());
            }
            //添加spuSaleAttrValue
            List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
            for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                if (spuSaleAttrValue != null) {
                    spuSaleAttrValue.setSpuId(spuInfo.getId());
                    spuSaleAttrValue.setSaleAttrName(spuSaleAttr.getSaleAttrName());
                }
            }
            spuSaleAttrValueService.saveBatch(spuSaleAttrValueList);
        }
        spuSaleAttrService.saveBatch(spuSaleAttrList);
    }
}




