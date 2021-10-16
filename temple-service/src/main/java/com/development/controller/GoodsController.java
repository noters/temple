package com.development.controller;

import com.development.dto.GoodsDto;
import com.development.dto.PageDto;
import com.development.entity.Goods;
import com.development.entity.GoodsClass;
import com.development.entity.GoodsDetail;
import com.development.service.GoodsClassService;
import com.development.service.GoodsDetailService;
import com.development.service.GoodsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    private static Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsClassService goodsClassService;
    @Autowired
    private GoodsDetailService goodsDetailService;

    private ObjectMapper mapper = new ObjectMapper();

    // 根据商品类别获取所有商品
    @RequestMapping("/list")
    @ResponseBody
    public String list(@RequestBody PageDto pageDto) {
        log.debug("商品类别: " + pageDto);
        Goods goods = new Goods();
        goods.setGoodsClass(pageDto.getGoodsClassId());
        List<Goods> goodsList = goodsService.queryList(goods);
        List<GoodsDto> list = setImage(goodsList, true);
        try {
            String result = mapper.writeValueAsString(list);
            log.debug("结果: " + result);
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{\"code\": \"1\", \"message\": \"失败\"}";
    }

    // 根据商品类别获取所有商品，分页
    @RequestMapping("/listPage")
    @ResponseBody
    public String listPage(@RequestBody PageDto pageDto) {
        log.debug("商品类别: " + pageDto);
        List<Goods> goodsList = goodsService.queryListPage(pageDto);
        List<GoodsDto> list = setImage(goodsList, false);
        try {
            String result = mapper.writeValueAsString(list);
            log.debug("结果: " + result);
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{\"code\": \"1\", \"message\": \"失败\"}";
    }

    private List<GoodsDto> setImage(List<Goods> goodsList, boolean goodsClassNameFlag) {
        List<GoodsDto> list = new ArrayList<>();
        for (Goods goods : goodsList) {
            GoodsDto goodsDto = new GoodsDto();
            BeanUtils.copyProperties(goods, goodsDto);
            list.add(goodsDto);
            if (goodsClassNameFlag) {
                GoodsClass goodsClass = new GoodsClass();
                goodsClass.setId(goodsDto.getGoodsClass());
                List<GoodsClass> goodsClassList = goodsClassService.queryList(goodsClass);
                if (goodsClassList != null && goodsClassList.size() > 0) {
                    String name = goodsClassList.get(0).getName();
                    goodsDto.setGoodsClassName(name);
                }
            }
            GoodsDetail goodsDetail = new GoodsDetail();
            goodsDetail.setGoods(goods.getId());
            goodsDetail.setType("1");//类型，0缩略图，1主图，2详情图，3描述文字
            List<GoodsDetail> goodsDetailList = goodsDetailService.queryList(goodsDetail);
            if (goodsDetailList != null && goodsDetailList.size() > 0) {
                String image = goodsDetailList.get(0).getImage();
                goodsDto.setImage(image);
            }
        }
        return list;
    }
}
