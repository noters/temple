package com.development.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.development.entity.GoodsItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsItemMapper extends BaseMapper<GoodsItem> {

    List<GoodsItem> queryList(GoodsItem goodsItem);
}
