package com.atguigu.gmall.model.vo.search;

import lombok.Data;

/**
 * @author wangwqiang
 * date 2022/9/5
 * @version 1.0
 */
@Data
public class OrderMapVo {
    //排序类型。1.综合 2.价格
    private String type;
    //排序方式
    private String sort;
}
