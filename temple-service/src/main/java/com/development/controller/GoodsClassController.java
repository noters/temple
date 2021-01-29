package com.development.controller;

import com.development.entity.GoodsClass;
import com.development.service.GoodsClassService;
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
@RequestMapping("/goodsClass")
public class GoodsClassController {

    private static Logger log = LoggerFactory.getLogger(GoodsClassController.class);

    @Autowired
    private GoodsClassService goodsClassService;

    private ObjectMapper mapper = new ObjectMapper();

    // 获取所有商品类别
    @RequestMapping("/list")
    @ResponseBody
    public String list(@RequestBody String parameter) {
        log.debug("商品类别: " + parameter);
        List<GoodsClass> list = goodsClassService.queryList(new GoodsClass());
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
