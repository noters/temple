package com.development.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.development.entity.GoodsDetail;

import java.util.List;

public interface GoodsDetailMapper extends BaseMapper<GoodsDetail> {

    List<GoodsDetail> queryList(GoodsDetail goodsDetail);
}
