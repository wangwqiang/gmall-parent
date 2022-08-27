package com.atguigu.gmall.activity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author wangwqiang
 * date 2022/8/26
 * @version 1.0
 */
@SpringCloudApplication
@MapperScan("com.atguigu.gmall.activity.mapper")
public class ActivityMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivityMainApplication.class,args);
    }
}
