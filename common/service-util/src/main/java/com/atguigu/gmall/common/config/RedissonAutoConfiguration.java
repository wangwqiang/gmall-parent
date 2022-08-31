package com.atguigu.gmall.common.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangwqiang
 * date 2022/8/30
 * @version 1.0
 */

//在redis准备好之后使用
@AutoConfigureAfter(RedisAutoConfiguration.class)
@Configuration
public class RedissonAutoConfiguration {
    @Autowired
    RedisProperties redisProperties;

    @Bean
    public RedissonClient redissonClient() {
        //1.创建一个配置
        Config config = new Config();
        String host = redisProperties.getHost();
        int port = redisProperties.getPort();
        String password = redisProperties.getPassword();
        //2.制定好redisson的配置项
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setPassword(password);
        //3.创建一个RedissonClient
        RedissonClient client = Redisson.create(config);
        return client;

    }
}
