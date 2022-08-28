package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.util.Jsons;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.atguigu.gmall.model.to.ValueSkuJsonTo;
import com.atguigu.gmall.product.mapper.SpuSaleAttrValueMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.atguigu.gmall.product.mapper.SpuSaleAttrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author wangwenqiang
* @description 针对表【spu_sale_attr(spu销售属性)】的数据库操作Service实现
* @createDate 2022-08-22 20:46:19
*/
@Service
public class SpuSaleAttrServiceImpl extends ServiceImpl<SpuSaleAttrMapper, SpuSaleAttr>
    implements SpuSaleAttrService{
    @Autowired
    SpuSaleAttrMapper spuSaleAttrMapper;

    /**
     * 根据spuId获取销售属性
     * @param spuId
     * @return
     */
    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(Long spuId) {
        return spuSaleAttrMapper.getSpuSaleAttrAndValue(spuId);
    }

    /**
     * 查询sku对应的spu定义的所有销售属性名和值。并且标记出当前sku是哪个
     * @param skuId
     * @param spuId
     * @return
     */
    @Override
    public List<SpuSaleAttr> getSpuSaleAttrAndValueMarkSku(Long skuId, Long spuId) {
        List<SpuSaleAttr> spuSaleAttrAndValueMarkSku = spuSaleAttrMapper.getSpuSaleAttrAndValueMarkSku(skuId, spuId);
        return spuSaleAttrAndValueMarkSku;
    }

    /**
     * 查询sku组合
     * @param spuId
     * @return
     */
    @Override
    public String getDetailSkuValueJson(Long spuId) {
        List<ValueSkuJsonTo> valueSkuJsonTos =
                spuSaleAttrMapper.getAllSkuSaleAttrValueJson(spuId);
        Map<String,Long> map = new HashMap<>();
        for (ValueSkuJsonTo valueSkuJsonTo : valueSkuJsonTos) {
            String valueJson = valueSkuJsonTo.getValueJson();
            Long skuJsonToSkuId = valueSkuJsonTo.getSkuId();
            map.put(valueJson,skuJsonToSkuId);
        }
        String json = Jsons.toStr(map);
        return json;
    }
}




