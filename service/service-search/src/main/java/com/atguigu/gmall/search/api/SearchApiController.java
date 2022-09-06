package com.atguigu.gmall.search.api;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.list.Goods;
import com.atguigu.gmall.model.vo.search.SearchParamVo;
import com.atguigu.gmall.model.vo.search.SearchResponseVo;
import com.atguigu.gmall.search.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangwqiang
 * date 2022/9/5
 * @version 1.0
 */
@RestController
@RequestMapping("/api/inner/rpc/search")
public class SearchApiController {
    @Autowired
    GoodsService goodsService;

    /**
     * 添加商品信息到es
     * @param goods
     * @return
     */
    @PostMapping("/goods")
    public Result savaGoods(@RequestBody Goods goods) {
        goodsService.savaGoods(goods);
        return Result.ok();
    }

    /**
     * 从es中删除商品信息
     * @param skuId
     * @return
     */
    @DeleteMapping("/goods/{skuId}")
    public Result deleteGoods(@PathVariable Long skuId) {
        goodsService.deleteGoods(skuId);
        return Result.ok();
    }

    /**
     * 商品检索
     * @param paramVo
     * @return
     */
    @PostMapping("/goods/search")
    public Result<SearchResponseVo> search(@RequestBody SearchParamVo paramVo) {
        SearchResponseVo searchResponseVo = goodsService.search(paramVo);
        return Result.ok(searchResponseVo);
    }

    /**
     * 更新热度分
     * @param skuId
     * @param score
     * @return
     */
    @GetMapping("/goods/hotScore/{skuId}")
    public Result updateHotScore(@PathVariable("skuId") Long skuId,
                                 @RequestParam("score") Long score) {
        goodsService.updateHotScore(skuId,score);
        return Result.ok();
    }
}
