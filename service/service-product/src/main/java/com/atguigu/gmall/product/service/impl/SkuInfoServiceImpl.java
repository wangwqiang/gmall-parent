package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.util.Jsons;
import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.model.to.ValueSkuJsonTo;
import com.atguigu.gmall.product.mapper.*;
import com.atguigu.gmall.product.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangwenqiang
 * @description 针对表【sku_info(库存单元表)】的数据库操作Service实现
 * @createDate 2022-08-22 20:46:18
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo>
        implements SkuInfoService {
    @Autowired
    SkuInfoMapper skuInfoMapper;
    @Autowired
    SkuImageService skuImageService;
    @Autowired
    SkuAttrValueService skuAttrValueService;
    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    BaseCategory3Mapper baseCategory3Mapper;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;
    @Autowired
    SpuSaleAttrMapper spuSaleAttrMapper;

    /**
     * sku上架
     *
     * @param skuId
     */
    @Override
    public void onSale(Long skuId) {
        skuInfoMapper.updateIsSale(skuId, 1);
    }

    /**
     * sku下架
     *
     * @param skuId
     */
    @Override
    public void cancelSale(Long skuId) {
        skuInfoMapper.updateIsSale(skuId, 0);
    }

    /**
     * 添加sku
     *
     * @param skuInfo
     */
    @Override
    @Transactional
    public void saveSkuInfoAndValue(SkuInfo skuInfo) {
        //添加sku_info
        skuInfoMapper.insert(skuInfo);
        //添加sku_image
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        for (SkuImage skuImage : skuImageList) {
            skuImage.setSkuId(skuInfo.getId());
        }
        skuImageService.saveBatch(skuImageList);
        //添加sku_attr_value
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        for (SkuAttrValue skuAttrValue : skuAttrValueList) {
            skuAttrValue.setSkuId(skuInfo.getId());
        }
        skuAttrValueService.saveBatch(skuAttrValueList);
        //添加sku_sale_attr_value
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
            skuSaleAttrValue.setSkuId(skuInfo.getId());
            skuSaleAttrValue.setSpuId(skuInfo.getSpuId());
        }
        skuSaleAttrValueService.saveBatch(skuSaleAttrValueList);

    }

    /**
     * 查询sku详情信息
     *
     * @param skuId
     * @return
     */
    @Deprecated
    @Override
    public SkuDetailTo getSkuDetail(Long skuId) {
        SkuDetailTo skuDetailTo = new SkuDetailTo();
        //1.查询skuInfo基本信息
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        skuDetailTo.setSkuInfo(skuInfo);
        //图片列表
        List<SkuImage> skuImageList = skuImageService.selectImageList(skuId);
        skuInfo.setSkuImageList(skuImageList);
        //2.价格
        BigDecimal price = get1010Price(skuId);
        skuDetailTo.setPrice(price);
        //3.查询三级分类
        CategoryViewTo categoryViewTo = baseCategory3Mapper.getCategory(skuInfo.getCategory3Id());
        skuDetailTo.setCategoryViewTo(categoryViewTo);
        //4.查询spuSaleAttrList
        List<SpuSaleAttr> spuSaleAttrAndValue = spuSaleAttrMapper.getSpuSaleAttrAndValueMarkSku(skuInfo.getSpuId(),skuId);
        skuDetailTo.setSpuSaleAttrList(spuSaleAttrAndValue);
        //5.查询 valuesSkuJson
        List<ValueSkuJsonTo> valueSkuJsonTos =  spuSaleAttrMapper.getAllSkuSaleAttrValueJson(skuInfo.getSpuId());
        Map<String,Long> map = new HashMap<>();
        for (ValueSkuJsonTo valueSkuJsonTo : valueSkuJsonTos) {
            String valueJson = valueSkuJsonTo.getValueJson();
            Long skuJsonToSkuId = valueSkuJsonTo.getSkuId();
            map.put(valueJson,skuJsonToSkuId);
        }
        String json = Jsons.toStr(map);
        skuDetailTo.setValuesSkuJson(json);
        return skuDetailTo;
    }

    /**
     * 查询实时价格
     * @param skuId
     * @return
     */
    @Override
    public BigDecimal get1010Price(Long skuId) {
        BigDecimal price = skuInfoMapper.get1010Price(skuId);
        return price;
    }

    /**
     * 查询skuInfo基本信息
     * @param skuId
     * @return
     */
    @Override
    public SkuInfo getDetailSkuInfo(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        return skuInfo;
    }

    /**
     * 查询图片信息
     * @param skuId
     * @return
     */
    @Override
    public List<SkuImage> getDetailSkuImages(Long skuId) {
        List<SkuImage> skuImageList = skuImageService.selectImageList(skuId);
        return skuImageList;
    }

    /**
     * 查询sku实时价格
     * @param skuId
     * @return
     */
    @Override
    public BigDecimal getDetailSku1010Price(Long skuId) {
        BigDecimal price = get1010Price(skuId);
        return price;
    }
}




