package com.atguigu.gmall.model.vo.user;

import lombok.Data;

/**
 * @author wangwqiang
 * date 2022/9/6
 * @version 1.0
 */
@Data
public class LoginSuccessVo {
    private String token;
    private String nickName;
}
