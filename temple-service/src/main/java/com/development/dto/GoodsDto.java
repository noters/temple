package com.development.dto;

import com.development.entity.Goods;
import com.development.entity.GoodsClass;
import com.development.entity.GoodsDetail;
import com.development.entity.GoodsItem;

import java.util.List;

public class GoodsDto extends Goods {

    private String image;
    private List<GoodsClass> goodsClassList;
    private List<GoodsItem> goodsItemList;
    private List<GoodsDetail> goodsDetailList;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<GoodsClass> getGoodsClassList() {
        return goodsClassList;
    }

    public void setGoodsClassList(List<GoodsClass> goodsClassList) {
        this.goodsClassList = goodsClassList;
    }

    public List<GoodsItem> getGoodsItemList() {
        return goodsItemList;
    }

    public void setGoodsItemList(List<GoodsItem> goodsItemList) {
        this.goodsItemList = goodsItemList;
    }

    public List<GoodsDetail> getGoodsDetailList() {
        return goodsDetailList;
    }

    public void setGoodsDetailList(List<GoodsDetail> goodsDetailList) {
        this.goodsDetailList = goodsDetailList;
    }
}
