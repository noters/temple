package com.development.controller;

import com.development.dto.Response;
import com.development.dto.TradeDto;
import com.development.service.TradeService;
import com.development.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/trade")
public class TradeController {
    private static Logger log = LoggerFactory.getLogger(TradeController.class);

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/create")
    @ResponseBody
    public Object create(HttpServletRequest request, @RequestBody TradeDto tradeDto) {
        Response response = new Response();
        tradeDto.setIp(StringUtil.getIpAddress(request));
        try {
            return tradeService.create(tradeDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setFail("1", "失败");
        return response;
    }
}
