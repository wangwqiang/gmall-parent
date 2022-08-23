package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.mapper.BaseAttrValueMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangwenqiang
 * @description 针对表【base_attr_info(属性表)】的数据库操作Service实现
 * @createDate 2022-08-22 19:45:49
 */
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo>
        implements BaseAttrInfoService {

    @Autowired
    BaseAttrInfoMapper attrInfoMapper;
    @Autowired
    BaseAttrValueMapper attrValueMapper;

    /**
     * 查询平台属性列表
     *
     * @param c1Id
     * @param c2Id
     * @param c3Id
     * @return
     */
    @Override
    public List<BaseAttrInfo> getAttrInfoAndValueByCategoryId(Long c1Id, Long c2Id, Long c3Id) {
        List<BaseAttrInfo> infos = attrInfoMapper.getAttrInfoAndValueByCategoryId(c1Id, c2Id, c3Id);
        return infos;
    }

    /**
     * 新增、修改属性
     * @param baseAttrInfo
     */
    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        if (baseAttrInfo.getId() == null) {
            //id不存在，新增属性
            addBaseAttrInfo(baseAttrInfo);
        } else {
            //id存在，修改属性
            updateBaseAttrInfo(baseAttrInfo);
        }
    }

    //修改属性
    private void updateBaseAttrInfo(BaseAttrInfo baseAttrInfo) {
        //删除
        //修改属性名
        attrInfoMapper.updateById(baseAttrInfo);
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        //1.删除
        List<Long> ids = new ArrayList();
        for (BaseAttrValue attrValue : attrValueList) {
            Long id = attrValue.getId();
            if (id != null) {
                ids.add(id);
            }
        }
        //delete * from base_attr_value where attr_id = 11 and id is not in(59,60)
        if (ids.size() > 0) {
            //部分删除
            LambdaQueryWrapper<BaseAttrValue> deleteWrapper = new LambdaQueryWrapper<>();
            deleteWrapper.eq(BaseAttrValue::getAttrId, baseAttrInfo.getId());
            deleteWrapper.notIn(BaseAttrValue::getId, ids);
            attrValueMapper.delete(deleteWrapper);
        }else {
            //全部删除
            LambdaQueryWrapper<BaseAttrValue> deleteWrapper = new LambdaQueryWrapper<>();
            deleteWrapper.eq(BaseAttrValue::getAttrId, baseAttrInfo.getId());
            attrValueMapper.delete(deleteWrapper);
        }

        //2.修改
        for (BaseAttrValue attrValue : attrValueList) {
            //1.属性id不存在，新增
            if (attrValue.getId() == null) {
                attrValue.setAttrId(baseAttrInfo.getId());
                attrValueMapper.insert(attrValue);
            }
            //2.属性id存在，更新
            if (attrValue.getId() != null) {
                attrValueMapper.updateById(attrValue);
            }
        }
    }

    //新增属性
    private void addBaseAttrInfo(BaseAttrInfo baseAttrInfo) {
        //保存属性名
        attrInfoMapper.insert(baseAttrInfo);
        for (BaseAttrValue attrValue : baseAttrInfo.getAttrValueList()) {
            //保存属性值
            attrValue.setAttrId(baseAttrInfo.getId());
            attrValueMapper.insert(attrValue);
        }
    }
}




