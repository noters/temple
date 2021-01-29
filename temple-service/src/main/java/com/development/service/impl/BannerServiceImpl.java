package com.development.service.impl;

import com.development.entity.Banner;
import com.development.mapper.BannerMapper;
import com.development.service.BannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    private static Logger log = LoggerFactory.getLogger(BannerServiceImpl.class);

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> queryList(Banner banner) {
        return bannerMapper.queryList(banner);
    }
}
