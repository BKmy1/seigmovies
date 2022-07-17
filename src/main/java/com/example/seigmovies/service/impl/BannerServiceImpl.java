package com.example.seigmovies.service.impl;

import com.example.seigmovies.entity.Banner;
import com.example.seigmovies.mapper.BannerMapper;
import com.example.seigmovies.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> getBannerList() {
        return bannerMapper.getBannerList();
    }
}
