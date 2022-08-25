package com.atguigu.gmall.product.config.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangwqiang
 * date 2022/8/25
 * @version 1.0
 */
@Configuration
public class MinioAutoConfiguration {
    @Autowired
    MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() throws Exception {
        // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
        MinioClient minioClient = new MinioClient(
                minioProperties.getEndpoint(),
                minioProperties.getAccessKey(),
                minioProperties.getSecretKey());

        // 检查存储桶是否已经存在
        String bucketName = minioProperties.getBucketName();
        boolean isExist = minioClient.bucketExists(bucketName);
        if(!isExist) {
            minioClient.makeBucket(bucketName);
        }
        return minioClient;
    }
}
