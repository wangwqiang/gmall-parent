package com.atguigu.gmall.model.to;

import lombok.Data;

import java.util.List;

/**
 * @author wangwqiang
 * date 2022/8/26
 * @version 1.0
 */
@Data
public class CategoryTreeTo {
    private Long categoryId;
    private String categoryName;
    private List<CategoryTreeTo> categoryChild;
}
