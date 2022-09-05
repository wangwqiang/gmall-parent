package com.atguigu.gmall.model.vo.search;

import lombok.Data;

import java.util.List;

/**
 * @author wangwqiang
 * date 2022/9/5
 * @version 1.0
 */
@Data
public class AttrVo {
    private Long attrId;
    private String attrName;
    private List<String> attrValueList;

}
