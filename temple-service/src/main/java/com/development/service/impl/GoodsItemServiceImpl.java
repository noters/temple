package com.development.service.impl;

import com.development.entity.GoodsItem;
import com.development.mapper.GoodsItemMapper;
import com.development.service.GoodsItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsItemServiceImpl implements GoodsItemService {

    private static Logger log = LoggerFactory.getLogger(GoodsItemServiceImpl.class);

    @Autowired
    private GoodsItemMapper goodsItemMapper;

    @Override
    public List<GoodsItem> queryList(GoodsItem goodsItem) {
        return goodsItemMapper.queryList(goodsItem);
    }
}
