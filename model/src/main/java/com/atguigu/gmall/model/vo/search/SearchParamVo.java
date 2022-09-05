package com.atguigu.gmall.model.vo.search;

import lombok.Data;

/**
 * @author wangwqiang
 * date 2022/9/5
 * @version 1.0
 */
@Data
public class SearchParamVo {
    private Long category1Id;
    private Long category2Id;
    private Long category3Id;
    private String keyword;
    private String trademark;
    private String[] props;
    private Integer pageNo = 1;
    private String order = "1:desc";
}
