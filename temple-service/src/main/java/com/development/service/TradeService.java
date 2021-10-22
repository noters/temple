package com.development.service;

import com.development.dto.PageDto;
import com.development.dto.TradeDto;
import com.development.entity.Trade;

import java.util.List;

public interface TradeService {

    List<Trade> queryListPage(PageDto pageDto);

    String create(TradeDto tradeDto) throws Exception;
}
