package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangwqiang
 * date 2022/8/22
 * @version 1.0
 */
@RestController
@RequestMapping("/admin/product")
public class BaseTrademarkController {
    @Autowired
    BaseTrademarkService trademarkService;

    /**
     * 分页显示品牌列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/baseTrademark/{pageNum}/{pageSize}")
    public Result baseTrademark(@PathVariable("pageNum")Long pageNum,
                                @PathVariable("pageSize")Long pageSize){
        Page<BaseTrademark> page = new Page<>(pageNum,pageSize);
        Page<BaseTrademark> trademarkPage = trademarkService.page(page);
        return Result.ok(trademarkPage);
    }

    /**
     * 添加品牌
     * @param baseTrademark
     * @return
     */
    @PostMapping("/baseTrademark/save")
    public Result save(@RequestBody BaseTrademark baseTrademark){
        trademarkService.save(baseTrademark);
        return Result.ok();
    }

    /**
     * 修改品牌信息
     * @param baseTrademark
     * @return
     */
    @PutMapping("/baseTrademark/update")
    public Result update(@RequestBody BaseTrademark baseTrademark){
        trademarkService.updateById(baseTrademark);
        return Result.ok();
    }

    /**
     * 删除品牌
     * @param id
     * @return
     */
    @DeleteMapping("/baseTrademark/remove/{id}")
    public Result remove(@PathVariable("id")Long id){
        trademarkService.removeById(id);
        return Result.ok();
    }

    /**
     * 根据id查询品牌信息
     * @param id
     * @return
     */
    @GetMapping("/baseTrademark/get/{id}")
    public Result getById(@PathVariable("id")Long id){
        BaseTrademark baseTrademark = trademarkService.getById(id);
        return Result.ok(baseTrademark);
    }

}
