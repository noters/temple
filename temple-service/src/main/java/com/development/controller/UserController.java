package com.development.controller;

import com.development.dto.UserDto;
import com.development.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    // 根据code获取openid和session_key
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody UserDto userDto) {
        log.debug("登录: " + userDto);
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + userDto.getAppid() +
                "&secret=" + userDto.getSecret() + "&js_code=" + userDto.getCode() + "&grant_type=authorization_code";
        try {
            String result = HttpClientUtil.post(url, "");
            log.debug("结果: " + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"code\": \"1\", \"message\": \"失败\"}";
    }
}
