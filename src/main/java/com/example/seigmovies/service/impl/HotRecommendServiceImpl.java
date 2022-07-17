package com.example.seigmovies.service.impl;

import com.example.seigmovies.entity.Video;
import com.example.seigmovies.mapper.HotRecommendMapper;
import com.example.seigmovies.service.HotReommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotRecommendServiceImpl implements HotReommendService {

    @Autowired
    private HotRecommendMapper hotRecommendMapper;

    @Override
    public List<Video> getHotVideoList() {
        return hotRecommendMapper.getHotVideoList();
    }
}
