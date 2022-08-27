package com.atguigu.gmall.model.to;

import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wangwqiang
 * date 2022/8/26
 * @version 1.0
 */
@Data
public class SkuDetailTo {
    private SkuInfo skuInfo;
    private BigDecimal price;
    private List<SpuSaleAttr> spuSaleAttrList;
    private CategoryViewTo categoryViewTo;
    private String valuesSkuJson;
}
