package com.atguigu.gmall.model.vo.search;

import com.atguigu.gmall.model.list.Goods;
import com.atguigu.gmall.model.list.SearchAttr;
import lombok.Data;

import java.util.List;

/**
 * @author wangwqiang
 * date 2022/9/5
 * @version 1.0
 */
@Data
public class SearchResponseVo {
    //参数
    private SearchParamVo searchParam;
    private String trademarkParam;
    private List<SearchAttr> propsParamList;

    private List<TrademarkVo> trademarkList;
    private List<AttrVo> attrsList;
    private OrderMapVo orderMap;
    private List<Goods> goodsList;
    //分页
    private Integer pageNo;
    private Integer totalPages;
    //地址栏拼接参数
    private String urlParam;
}
