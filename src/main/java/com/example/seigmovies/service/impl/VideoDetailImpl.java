package com.example.seigmovies.service.impl;

import com.example.seigmovies.entity.Video;
import com.example.seigmovies.mapper.VideoDetailMapper;
import com.example.seigmovies.service.VideoDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VideoDetailImpl implements VideoDetailService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private VideoDetailMapper videoDetailMapper;

    @Override
    public Video selectVideoById(String videoId) {
        Video video = null;
        Object object = redisTemplate.opsForValue().get("video_" + videoId);
        if (object != null) {
            video = (Video) object;
        } else {
            video  = videoDetailMapper.selectVideoById(videoId);
            if (video  != null) {
                //将查询结果缓存，并设置有效期为一星期
                redisTemplate.opsForValue().set("video_" + videoId, video, 7, TimeUnit.DAYS);
            }
        }
        return video;
    }

    @Override
    public int updateVideoWeekCount(String videoId) {
        return videoDetailMapper.updateVideoWeekCount(videoId);
    }

    @Override
    public int updateVideoMonthCount(String videoId) {
        return videoDetailMapper.updateVideoMonthCount(videoId);
    }

    @Override
    public int updateVideoYearCount(String videoId) {
        return videoDetailMapper.updateVideoYearCount(videoId);
    }
}
