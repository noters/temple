package com.development.service;

import com.development.entity.GoodsItem;

import java.util.List;

public interface GoodsItemService {

    List<GoodsItem> queryList(GoodsItem goodsItem);
}
