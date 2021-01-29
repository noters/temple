package com.development.controller;

import com.development.entity.Banner;
import com.development.service.BannerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/banner")
public class BannerController {

    private static Logger log = LoggerFactory.getLogger(BannerController.class);

    @Autowired
    private BannerService bannerService;

    private ObjectMapper mapper = new ObjectMapper();

    // 获取所有轮播图
    @RequestMapping("/list")
    @ResponseBody
    public String list(@RequestBody String parameter) {
        log.debug("轮播图: " + parameter);
        List<Banner> list = bannerService.queryList(new Banner());
        try {
            String result = mapper.writeValueAsString(list);
            log.debug("结果: " + result);
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{\"code\": \"1\", \"message\": \"失败\"}";
    }
}
