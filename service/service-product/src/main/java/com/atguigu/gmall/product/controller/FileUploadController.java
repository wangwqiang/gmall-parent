package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import io.minio.MinioClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangwqiang
 * date 2022/8/23
 * @version 1.0
 */
@RestController
@RequestMapping("/admin/product")
public class FileUploadController {

    @PostMapping("/fileUpload")
    public Result fileUpload(){
//        MinioClient minioClient = new MinioClient();
        return Result.ok();
    }

//@CookieValue("jsessionid")String jsessionid
    @PostMapping("/test")
    public Result test(@RequestParam("name")String name,
                       @RequestParam("password")String password,
                       @RequestPart("head")MultipartFile[] head,
                       @RequestPart("photo")MultipartFile photo,
                       @RequestPart("idCard")MultipartFile idCard,
                       @RequestParam("hobby")String[] hobby,
                       @RequestHeader("Host")String Host){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("password",password);
        map.put("head",head.length);
        map.put("photo",photo.getSize());
        map.put("idCard",idCard.getSize());
        map.put("hobby",hobby);
        map.put("Host",Host);
//        map.put("jsessionid",jsessionid);
        return Result.ok(map);
    }
}
