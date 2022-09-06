package com.atguigu.gmall.user.service.impl;

import com.atguigu.gmall.common.constant.SysRedisConst;
import com.atguigu.gmall.common.util.Jsons;
import com.atguigu.gmall.common.util.MD5;
import com.atguigu.gmall.model.user.UserInfo;
import com.atguigu.gmall.model.vo.user.LoginSuccessVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.user.service.UserInfoService;
import com.atguigu.gmall.user.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author wangwenqiang
 * @description 针对表【user_info(用户表)】的数据库操作Service实现
 * @createDate 2022-09-06 18:18:43
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 登录
     * @param userInfo
     * @return
     */
    @Override
    public LoginSuccessVo login(UserInfo userInfo) {
        LoginSuccessVo vo = new LoginSuccessVo();
        //1.到数据库查询用户
        String passwd = MD5.encrypt(userInfo.getPasswd());
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getLoginName, userInfo.getLoginName())
                .eq(UserInfo::getPasswd, passwd);
        UserInfo info = userInfoMapper.selectOne(queryWrapper);

        //2.判断用户是否存在
        if (info != null) {
            //生成token
            String token = UUID.randomUUID().toString().replace("-", "");
            //将信息放在redis
            redisTemplate.opsForValue().set(SysRedisConst.LOGIN_TOKEN + token,
                    Jsons.toStr(info),
                    7, TimeUnit.DAYS);
            //返回
            vo.setToken(token);
            vo.setNickName(info.getNickName());
            return vo;
        }
        return null;
    }

    /**
     * 退出
     * @param token
     */
    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }
}




