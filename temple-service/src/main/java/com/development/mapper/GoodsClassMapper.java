package com.development.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.development.entity.GoodsClass;

import java.util.List;

public interface GoodsClassMapper extends BaseMapper<GoodsClass> {

    List<GoodsClass> queryList(GoodsClass goodsClass);
}
