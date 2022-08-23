package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrValue;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import com.atguigu.gmall.product.mapper.BaseAttrValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author wangwenqiang
* @description 针对表【base_attr_value(属性值表)】的数据库操作Service实现
* @createDate 2022-08-22 19:45:49
*/
@Service
public class BaseAttrValueServiceImpl extends ServiceImpl<BaseAttrValueMapper, BaseAttrValue>
    implements BaseAttrValueService{

    @Autowired
    BaseAttrValueMapper attrValueMapper;

    /**
     * 根据属性id查询属性值
     * @param attrId
     * @return
     */
    @Override
    public List<BaseAttrValue> getAttrValueList(Long attrId) {
        LambdaQueryWrapper<BaseAttrValue> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(BaseAttrValue::getAttrId,attrId);
        List<BaseAttrValue> baseAttrValues = attrValueMapper.selectList(queryWrapper);
        return baseAttrValues;
    }
}




