package com.development.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.development.entity.Trade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeMapper extends BaseMapper<Trade> {

    List<Trade> queryListPage(Page<Trade> page, @Param("trade") Trade trade);
}
