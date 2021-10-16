package com.development.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.development.dto.PageDto;
import com.development.entity.Goods;
import com.development.mapper.GoodsMapper;
import com.development.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private static Logger log = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> queryList(Goods goods) {
        return goodsMapper.queryList(goods);
    }

    @Override
    public List<Goods> queryListPage(PageDto pageDto) {
        Page<Goods> page = new Page<>(pageDto.getCurrent(), pageDto.getSize());
        Goods goods = new Goods();
        goods.setGoodsClass(pageDto.getGoodsClassId());
        List<Goods> list = goodsMapper.queryListPage(page, goods);
        page = page.setRecords(list);
        return list;
    }
}
