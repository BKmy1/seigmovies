package com.example.seigmovies.service;

import com.example.seigmovies.entity.Video;

import java.util.List;

public interface RankService {

    public List<Video> getWeekRankList();

    public List<Video> getMonthRankList();

    public List<Video> getYearRankList();
}
