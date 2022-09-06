package com.atguigu.gmall.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangwqiang
 * date 2022/9/6
 * @version 1.0
 */
@Controller
public class LoginController {
    /**
     * 去到登录页面
     * @param originUrl
     * @param model
     * @return
     */
    @GetMapping("/login.html")
    public String login(@RequestParam("originUrl") String originUrl, Model model) {
        model.addAttribute("originUrl",originUrl);
        return "login";
    }
}
