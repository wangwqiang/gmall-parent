package com.atguigu.gmall.item;

import com.atguigu.gmall.common.annotation.EnableRedissonClient;
import com.atguigu.gmall.common.annotation.EnableThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangwqiang
 * date 2022/8/26
 * @version 1.0
 */
@SpringCloudApplication
@EnableFeignClients
@EnableThreadPool
public class ItemMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemMainApplication.class,args);
    }
}
