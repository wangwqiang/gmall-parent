package com.atguigu.gmall.user.service;

import com.atguigu.gmall.model.user.UserInfo;
import com.atguigu.gmall.model.vo.user.LoginSuccessVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wangwenqiang
* @description 针对表【user_info(用户表)】的数据库操作Service
* @createDate 2022-09-06 18:18:43
*/
public interface UserInfoService extends IService<UserInfo> {

    LoginSuccessVo login(UserInfo userInfo);

    void logout(String token);
}