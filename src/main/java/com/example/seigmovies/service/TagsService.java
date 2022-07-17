package com.example.seigmovies.service;

import com.example.seigmovies.entity.VideoTime;
import com.example.seigmovies.entity.VideoType;

import java.util.List;

public interface TagsService {
    public List<VideoType> getAllVideoTypes();

    public List<VideoTime> getAllVideoTime();
}
