package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseCategory2;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.service.BaseCategory2Service;
import com.atguigu.gmall.product.mapper.BaseCategory2Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangwenqiang
 * @description 针对表【base_category2(二级分类表)】的数据库操作Service实现
 * @createDate 2022-08-22 18:53:06
 */
@Service
public class BaseCategory2ServiceImpl extends ServiceImpl<BaseCategory2Mapper, BaseCategory2>
        implements BaseCategory2Service {
    /**
     * 查询二级分类列表
     * @param c1Id
     * @return
     */
    @Override
    public List<BaseCategory2> getCategory2(Long c1Id) {
        List<BaseCategory2> category2List = this.list(new LambdaQueryWrapper<BaseCategory2>()
                .eq(BaseCategory2::getCategory1Id, c1Id));
        return category2List;
    }
}




