package com.atguigu.gmall.user.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.common.result.ResultCodeEnum;
import com.atguigu.gmall.model.user.UserInfo;
import com.atguigu.gmall.model.vo.user.LoginSuccessVo;
import com.atguigu.gmall.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangwqiang
 * date 2022/9/6
 * @version 1.0
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserInfoService userInfoService;

    /**
     * 登录
     * @param userInfo
     * @return
     */
    @PostMapping("/passport/login")
    public Result login(@RequestBody UserInfo userInfo) {
        LoginSuccessVo loginSuccessVo = userInfoService.login(userInfo);
        if (loginSuccessVo != null) {
            return Result.ok(loginSuccessVo);
        }
        return Result.build("", ResultCodeEnum.LOGIN_ERROR);
    }

    /**
     * 退出
     * @param token
     * @return
     */
    @GetMapping("/passport/logout")
    public Result logout(@RequestHeader("token") String token){
        userInfoService.logout(token);
        return Result.ok();
    }
}
