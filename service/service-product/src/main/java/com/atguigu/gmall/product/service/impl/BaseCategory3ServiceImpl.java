package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.constant.SysRedisConst;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.model.to.CategoryTreeTo;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.starter.cache.annotation.GmallCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import com.atguigu.gmall.product.mapper.BaseCategory3Mapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    BaseCategory3Mapper baseCategory3Mapper;

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

    /**
     * 查询首页信息
     * @return
     */
    @GmallCache(cacheKey = SysRedisConst.CACHE_CATEGORYS) //categorys
    @Override
    public List<CategoryTreeTo> getCategoryTree() {
        return  baseCategory3Mapper.getCategoryTree();
    }

    /**
     * 查询分类
     * @param c3Id
     * @return
     */
    @Override
    public CategoryViewTo getCategoryView(Long c3Id) {
        return baseCategory3Mapper.getCategory(c3Id);
    }
}




