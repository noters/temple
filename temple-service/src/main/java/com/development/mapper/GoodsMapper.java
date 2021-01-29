package com.development.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.development.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {

    List<Goods> queryList(Goods goods);

    List<Goods> queryListPage(Page<Goods> page, @Param("goods") Goods goods);
}
