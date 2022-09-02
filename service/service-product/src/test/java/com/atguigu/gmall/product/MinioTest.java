package com.atguigu.gmall.product;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.MinioException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author wangwqiang
 * date 2022/8/25
 * @version 1.0
 */
//@SpringBootTest
public class MinioTest {

    @Test
    public void uploadFileTest(){
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient("http://192.168.2.108:9000", "admin", "admin123456");

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists("gmall");
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为gmall的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket("gmall");
            }
            //D:\Java\尚品汇\资料\03 商品图片\品牌\oppo.png
            // 使用putObject上传一个文件到存储桶中。
            InputStream inputStream = new FileInputStream("D:\\Java\\尚品汇\\资料\\03 商品图片\\品牌\\oppo.png");
            PutObjectOptions options = new PutObjectOptions(inputStream.available(),-1);
            options.setContentType("image/png");
            minioClient.putObject("gmall","oppo.png",inputStream,options);
            System.out.println("图片上传成功");
        } catch(Exception e) {
            System.err.println("上传失败: " + e);
        }
    }
}
