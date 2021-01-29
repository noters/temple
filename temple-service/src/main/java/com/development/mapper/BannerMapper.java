package com.development.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.development.entity.Banner;

import java.util.List;

public interface BannerMapper extends BaseMapper<Banner> {

    List<Banner> queryList(Banner banner);
}
