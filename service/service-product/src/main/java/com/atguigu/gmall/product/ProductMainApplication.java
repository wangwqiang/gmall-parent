package com.atguigu.gmall.product;

import com.atguigu.gmall.common.annotation.EnableMybatisPlusConfig;
import com.atguigu.gmall.common.annotation.EnableRedissonClient;
import com.atguigu.gmall.common.config.Swagger2Config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wangwqiang
 * date 2022/8/22
 * @version 1.0
 */
@EnableScheduling
@Import(Swagger2Config.class)
@MapperScan("com.atguigu.gmall.product.mapper")
@SpringCloudApplication
@EnableMybatisPlusConfig
public class ProductMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductMainApplication.class,args);
    }
}
