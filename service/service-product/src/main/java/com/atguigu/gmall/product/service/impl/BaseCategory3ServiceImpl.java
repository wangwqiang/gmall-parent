package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseCategory3;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import com.atguigu.gmall.product.mapper.BaseCategory3Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangwenqiang
 * @description 针对表【base_category3(三级分类表)】的数据库操作Service实现
 * @createDate 2022-08-22 18:58:00
 */
@Service
public class BaseCategory3ServiceImpl extends ServiceImpl<BaseCategory3Mapper, BaseCategory3>
        implements BaseCategory3Service {
    /**
     * 查询三级分类列表
     * @param c2Id
     * @return
     */
    @Override
    public List<BaseCategory3> getCategory3(Long c2Id) {
        List<BaseCategory3> category3List = this.list(new LambdaQueryWrapper<BaseCategory3>()
                .eq(BaseCategory3::getCategory2Id, c2Id));
        return category3List;
    }
}




