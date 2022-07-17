package com.example.seigmovies.service.impl;

import com.example.seigmovies.entity.VideoTime;
import com.example.seigmovies.entity.VideoType;
import com.example.seigmovies.mapper.TagsMapper;
import com.example.seigmovies.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    public TagsMapper tagsMapper;

    @Override
    public List<VideoType> getAllVideoTypes() {
        List<VideoType> allVideoTypes = tagsMapper.getAllVideoTypes();
        return allVideoTypes;
    }

    @Override
    public List<VideoTime> getAllVideoTime() {
        List<VideoTime> allVideoTime = tagsMapper.getAllVideoTime();
        return allVideoTime;
    }
}
