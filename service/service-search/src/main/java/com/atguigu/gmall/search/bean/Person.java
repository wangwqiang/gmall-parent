package com.atguigu.gmall.search.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author wangwqiang
 * date 2022/9/3
 * @version 1.0
 */
@Data
@Document(indexName = "person",shards = 1,replicas = 1)
public class Person {
    @Id
    private Long id;
    @Field(type = FieldType.Text,analyzer = "ik_smart")
    private String name;
    @Field(type = FieldType.Keyword)
    private Integer age;
    @Field(type = FieldType.Text,analyzer = "ik_smart")
    private String address;
}
