package com.atguigu.gmall.search.service;

import com.atguigu.gmall.model.list.Goods;
import com.atguigu.gmall.model.vo.search.SearchParamVo;
import com.atguigu.gmall.model.vo.search.SearchResponseVo;

public interface GoodsService {
    void savaGoods(Goods goods);

    void deleteGoods(Long skuId);

    SearchResponseVo search(SearchParamVo paramVo);
}
