package com.development.service;

import com.development.dto.PageDto;
import com.development.entity.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> queryList(Goods goods);

    List<Goods> queryListPage(PageDto pageDto);
}
