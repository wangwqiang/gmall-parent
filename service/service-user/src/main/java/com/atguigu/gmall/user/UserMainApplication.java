package com.atguigu.gmall.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author wangwqiang
 * date 2022/9/6
 * @version 1.0
 */
@SpringCloudApplication
@MapperScan("com.atguigu.gmall.user.mapper")
public class UserMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserMainApplication.class,args);
    }
}
