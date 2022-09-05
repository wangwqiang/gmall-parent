package com.atguigu.gmall.web;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangwqiang
 * date 2022/8/26
 * @version 1.0
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.atguigu.gmall.feign.item",
        "com.atguigu.gmall.feign.product",
        "com.atguigu.gmall.feign.search"})
public class WebMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebMainApplication.class, args);
    }
}
