package com.example.seigmovies.service.impl;

import com.example.seigmovies.entity.Video;
import com.example.seigmovies.mapper.BestMapper;
import com.example.seigmovies.service.BestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BestServiceImpl implements BestService {

    @Autowired
    private BestMapper bestMapper;

    @Override
    public List<Video> getBestVideo() {
        return bestMapper.getBestVideo();
    }
}
