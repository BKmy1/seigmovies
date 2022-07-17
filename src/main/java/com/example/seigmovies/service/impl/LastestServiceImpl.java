package com.example.seigmovies.service.impl;

import com.example.seigmovies.entity.Video;
import com.example.seigmovies.mapper.LastestMapper;
import com.example.seigmovies.service.LastestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LastestServiceImpl implements LastestService {
    @Autowired
    private LastestMapper lastestMapper;

    @Override
    public List<Video> getLastestVideoList() {
        return lastestMapper.getLastestVideoList();
    }
}
