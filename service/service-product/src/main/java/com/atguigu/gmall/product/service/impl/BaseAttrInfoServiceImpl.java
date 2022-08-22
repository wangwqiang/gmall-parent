package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author wangwenqiang
* @description 针对表【base_attr_info(属性表)】的数据库操作Service实现
* @createDate 2022-08-22 19:45:49
*/
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo>
    implements BaseAttrInfoService{

    @Override
    public List<BaseAttrInfo> getAttrInfoList(Long c1Id, Long c2Id, Long c3Id) {

        return null;
    }
}




