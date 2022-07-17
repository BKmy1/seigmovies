package com.example.seigmovies.service.impl;

import com.example.seigmovies.entity.PageQo;
import com.example.seigmovies.entity.Video;
import com.example.seigmovies.mapper.VideoMapper;
import com.example.seigmovies.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> getVideoList() {
        return videoMapper.getVideoList();
    }

    @Override
    public List<Video> getVideoListTypeAndTime(String time, String type) {
        return videoMapper.getVideoListTypeAndTime(time,type);
    }

    @Override
    public List<Video> selectVideoByNameAndActor(String title) {
        return videoMapper.selectVideoByNameAndActor(title);
    }

    @Override
    public PageInfo<Video> queryAll(PageQo pageQo) {
        PageHelper.startPage(pageQo.getCurrentPage(),pageQo.getPageSize());
        List<Video> videoList = videoMapper.getVideoList();
        return new PageInfo<>(videoList);
    }

    @Override
    public Boolean addVideo(Video video) {
        return videoMapper.addVideo(video);
    }

    @Override
    public int deleteVideo(int id) {
        return videoMapper.deleteVideo(id);
    }

    @Override
    public int updateVideo(Video video) {
        return videoMapper.updateVideo(video);
    }

    @Override
    public Video selectVideoById(int videoId) {
        return videoMapper.selectVideoById(videoId);
    }

//    @Override
//    public List<Video> getVideoTimeList(String time) {
//        return videoMapper.getVideoTimeList(time);
//    }

//    @Override
//    public List<Video> getVideoTypeList(String type) {
//        return videoMapper.getVideoTypeList(type);
//    }

}
