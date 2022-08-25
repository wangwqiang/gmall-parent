package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.util.DateUtil;
import com.atguigu.gmall.product.config.minio.MinioProperties;
import com.atguigu.gmall.product.service.FileUploadService;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @author wangwqiang
 * date 2022/8/25
 * @version 1.0
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    MinioClient minioClient;
    @Autowired
    MinioProperties minioProperties;

    /**
     * 文件上传
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public String fileUpload(MultipartFile file) throws Exception {
        //1.创建minioClient对象
        //2.判断桶是否存在
        //3.向桶里上传文件
       //归类，日期作为文件夹
        String date = DateUtil.formatDate(new Date());
        //设置唯一的文件名
        String filename = UUID.randomUUID().toString().replace("-","")+ "_" + file.getOriginalFilename();
        String contentType = file.getContentType();
        //4.使用putObject上传一个文件到存储桶中。
        InputStream inputStream = file.getInputStream();
        PutObjectOptions options = new PutObjectOptions(file.getSize(),-1);
        options.setContentType(contentType);
        minioClient.putObject(minioProperties.getBucketName(),
                date + "/" + filename,   //自己指定的唯一名
                inputStream,options);
        //5.返回上传问价的可访问路径
        String url = minioProperties.getEndpoint() + minioProperties.getBucketName() + "/" + date + "/" + filename;
        return url;
    }
}
