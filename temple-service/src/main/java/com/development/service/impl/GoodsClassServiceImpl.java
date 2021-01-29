package com.development.service.impl;

import com.development.entity.GoodsClass;
import com.development.mapper.GoodsClassMapper;
import com.development.service.GoodsClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsClassServiceImpl implements GoodsClassService {

    private static Logger log = LoggerFactory.getLogger(GoodsClassServiceImpl.class);

    @Autowired
    private GoodsClassMapper goodsClassMapper;

    @Override
    public List<GoodsClass> queryList(GoodsClass goodsClass) {
        return goodsClassMapper.queryList(goodsClass);
    }
}
