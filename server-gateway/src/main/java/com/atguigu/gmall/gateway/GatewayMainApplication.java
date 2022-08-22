package com.atguigu.gmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author wangwqiang
 * date 2022/8/22
 * @version 1.0
 */
@SpringCloudApplication
public class GatewayMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMainApplication.class,args);
    }
}
