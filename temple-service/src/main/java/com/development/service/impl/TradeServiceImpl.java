package com.development.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.development.dto.Order;
import com.development.dto.PageDto;
import com.development.dto.Response;
import com.development.dto.TradeDto;
import com.development.entity.Goods;
import com.development.entity.GoodsItem;
import com.development.entity.Trade;
import com.development.mapper.GoodsItemMapper;
import com.development.mapper.GoodsMapper;
import com.development.mapper.TradeMapper;
import com.development.service.TradeService;
import com.development.util.HttpClientUtil;
import com.development.util.StringUtil;
import com.development.util.weixin.WXPayUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TradeServiceImpl implements TradeService {

    private static Logger log = LoggerFactory.getLogger(TradeServiceImpl.class);

    @Value("${weixin.merchantId}")
    private String merchantId;
    @Value("${weixin.merchantKey}")
    private String merchantKey;
    @Value("${weixin.body}")
    private String body;
    @Value("${weixin.notifyUrl}")
    private String notifyUrl;

    @Autowired
    private TradeMapper tradeMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsItemMapper goodsItemMapper;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Trade> queryListPage(PageDto pageDto) {
        Page<Trade> page = new Page<>(pageDto.getCurrent(), pageDto.getSize());
        Trade order = new Trade();
        order.setId(pageDto.getId());
        List<Trade> list = tradeMapper.queryListPage(page, order);
        page = page.setRecords(list);
        return list;
    }

    @Override
    public String create(TradeDto tradeDto) throws Exception {
        String goodsId = tradeDto.getGoodsId();
        Goods goods = goodsMapper.selectById(goodsId);
        String goodsItemId = tradeDto.getGoodsItemId();
        GoodsItem goodsItem = goodsItemMapper.selectById(goodsItemId);
        if (goods == null || goodsItem == null) {
            return null;
        }
        //写入订单表
        Trade trade = new Trade();
        BeanUtils.copyProperties(tradeDto, trade);
        String tradeNo = StringUtil.getRandom32();
        trade.setTradeNo(tradeNo);
        trade.setGoodsName(goods.getName());
        trade.setGoodsItemName(goodsItem.getName());
        trade.setPrice(goodsItem.getPrice());
        //状态，0已下单，1已支付
        trade.setStatus("0");
        //tradeMapper.insert(trade);

        Order order = new Order();
        order.setAppid(tradeDto.getAppid());
        order.setMchId(merchantId);
        order.setNonceStr(StringUtil.getRandom32());
        //order.setSign();
        order.setBody(body);
        order.setOutTradeNo(trade.getTradeNo());
        int totalFee = Integer.parseInt(StringUtil.getRenminbiCent(trade.getPrice()));
        order.setTotalFee(totalFee);
        order.setSpbillCreateIp(tradeDto.getIp());
        order.setNotifyUrl(notifyUrl);
        order.setTradeType("JSAPI");
        order.setOpenid(tradeDto.getOpenid());
        //Map<String, String> map = mapper.readValue(mapper.writeValueAsString(order), Map<String, String>.class);
        Map<String, String> map = StringUtil.getObjectToMap(order);
        String xml = WXPayUtil.generateSignedXml(map, merchantKey);

        /*httpPost.addHeader("Content-Type", "text/xml");
        httpPost.addHeader("User-Agent", USER_AGENT + " " + config.getMchID());*/
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        log.debug("请求地址：" + url + " 请求内容：" + xml);
        String resultXml = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>\n" +
                "   <openid><![CDATA[oUpF8uMuAJO_M2pxb1Q9zNjWeS6o]]></openid>\n" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>\n" +
                "   <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "</xml>";
        //String resultXml = HttpClientUtil.postXml(url, xml);
        log.debug("请求结果：" + resultXml);
        Map<String, String> resultMap = WXPayUtil.xmlToMap(resultXml);
        String returnCode = resultMap.get("return_code");
        String returnMessage = resultMap.get("return_msg");
        String message = "";
        String prepayId = null;
        Response response = new Response();
        if ("SUCCESS".equals(returnCode)) {
            String resultCode = resultMap.get("result_code");
            String errorCodeDesc = resultMap.get("err_code_des");
            if ("SUCCESS".equals(resultCode)) {
                //预支付交易会话标识
                prepayId = resultMap.get("prepay_id");
            } else {
                message = message + errorCodeDesc + "，";
            }
        } else {
            message = message + returnMessage;
        }
        if (message.length() > 0) {
            response.setFail("2", message);
            trade.setRemark(message);
        } else {
            trade.setRemark("prepayId:" + prepayId);
        }
        tradeMapper.insert(trade);
        if (prepayId != null) {
            Map<String, String> payMap = new HashMap<>();
            payMap.put("appId", tradeDto.getAppid());
            payMap.put("timeStamp", String.valueOf(System.currentTimeMillis()));
            payMap.put("nonceStr", StringUtil.getRandom32());
            payMap.put("package", "prepay_id=" + prepayId);
            payMap.put("signType", "MD5");
            String sign = WXPayUtil.generateSignature(map, merchantKey);
            payMap.put("paySign", sign);

            response.setSuccess(payMap);
        }
        return mapper.writeValueAsString(response);
    }
}
