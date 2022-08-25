package com.atguigu.gmall.product.config.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangwqiang
 * date 2022/8/25
 * @version 1.0
 */
@ConfigurationProperties(prefix = "app.minio")
@Data
@Component
public class MinioProperties {
    String endpoint;
    String accessKey;
    String secretKey;
    String bucketName;
}
