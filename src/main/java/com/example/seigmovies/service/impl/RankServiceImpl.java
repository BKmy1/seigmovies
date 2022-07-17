package com.example.seigmovies.service.impl;

import com.example.seigmovies.entity.Video;
import com.example.seigmovies.mapper.RankMapper;
import com.example.seigmovies.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RankMapper rankMapper;

    @Override
    public List<Video> getWeekRankList() {
        List<Video> weekRankList = rankMapper.getWeekRankList();
        for (Video weekVideo : weekRankList) {
//            redisTemplate.opsForValue().set("video_"+weekVideo.getVideoId(),weekVideo);
            redisTemplate.opsForHash().put("weekList", weekVideo.getVideoId(), weekVideo);
        }
        redisTemplate.opsForHash().values("weekList");
        return weekRankList;
    }

    @Override
    public List<Video> getMonthRankList() {
        List<Video> monthRankList = rankMapper.getMonthRankList();
        for (Video monthVideo : monthRankList) {
            redisTemplate.opsForHash().put("monthList", monthVideo.getVideoId(), monthVideo);
        }
        redisTemplate.opsForHash().values("monthList");
        return monthRankList;
    }

    @Override
    public List<Video> getYearRankList() {
        List<Video> yearRankList = rankMapper.getYearRankList();
        for (Video yearVideo : yearRankList) {
            redisTemplate.opsForHash().put("yearList", yearVideo.getVideoId(), yearVideo);
        }
        redisTemplate.opsForHash().values("yearList");
        return yearRankList;
    }
}
