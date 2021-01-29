package com.development.service.impl;

import com.development.entity.GoodsDetail;
import com.development.mapper.GoodsDetailMapper;
import com.development.service.GoodsDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsDetailServiceImpl implements GoodsDetailService {

    private static Logger log = LoggerFactory.getLogger(GoodsDetailServiceImpl.class);

    @Autowired
    private GoodsDetailMapper goodsDetailMapper;

    @Override
    public List<GoodsDetail> queryList(GoodsDetail goodsDetail) {
        return goodsDetailMapper.queryList(goodsDetail);
    }
}
