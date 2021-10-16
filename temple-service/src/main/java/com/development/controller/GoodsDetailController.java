package com.development.controller;

import com.development.dto.GoodsDto;
import com.development.dto.PageDto;
import com.development.entity.Goods;
import com.development.entity.GoodsClass;
import com.development.entity.GoodsDetail;
import com.development.entity.GoodsItem;
import com.development.service.GoodsClassService;
import com.development.service.GoodsDetailService;
import com.development.service.GoodsItemService;
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

import java.util.List;

@Controller
@RequestMapping("/goodsDetail")
public class GoodsDetailController {

    private static Logger log = LoggerFactory.getLogger(GoodsDetailController.class);

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsClassService goodsClassService;
    @Autowired
    private GoodsItemService goodsItemService;
    @Autowired
    private GoodsDetailService goodsDetailService;

    private ObjectMapper mapper = new ObjectMapper();

    // 根据商品获取商品详情
    @RequestMapping("/list")
    @ResponseBody
    public String list(@RequestBody PageDto pageDto) {
        log.debug("商品详情: " + pageDto);
        GoodsDto goodsDto = new GoodsDto();
        Goods goods = new Goods();
        goods.setId(pageDto.getId());
        List<Goods> goodsList = goodsService.queryList(goods);
        if (goodsList != null && goodsList.size() == 1) {
            BeanUtils.copyProperties(goodsList.get(0), goodsDto);
            GoodsClass goodsClass = new GoodsClass();
            goodsClass.setId(goodsDto.getGoodsClass());
            List<GoodsClass> goodsClassList = goodsClassService.queryList(goodsClass);
            goodsDto.setGoodsClassList(goodsClassList);
            if (goodsClassList != null && goodsClassList.size() == 1) {
                goodsDto.setGoodsClassName(goodsClassList.get(0).getName());
            }
            GoodsItem goodsItem = new GoodsItem();
            goodsItem.setGoods(goodsDto.getId());
            List<GoodsItem> goodsItemList = goodsItemService.queryList(goodsItem);
            goodsDto.setGoodsItemList(goodsItemList);
            GoodsDetail goodsDetail = new GoodsDetail();
            goodsDetail.setGoods(goodsDto.getId());
            List<GoodsDetail> goodsDetailList = goodsDetailService.queryList(goodsDetail);
            goodsDto.setGoodsDetailList(goodsDetailList);
            if (goodsDetailList != null && goodsDetailList.size() > 0) {
                goodsDto.setImage(goodsDetailList.get(0).getImage());
            }
            try {
                String result = mapper.writeValueAsString(goodsDto);
                log.debug("结果: " + result);
                return result;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return "{\"code\": \"1\", \"message\": \"失败\"}";
    }
}
