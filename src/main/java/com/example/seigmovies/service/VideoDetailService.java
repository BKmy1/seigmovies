package com.example.seigmovies.service;

import com.example.seigmovies.entity.Video;
import org.apache.ibatis.annotations.Param;

public interface VideoDetailService {

    public Video selectVideoById(@Param("videoId") String videoId);

    public int updateVideoWeekCount(@Param("videoId") String videoId);

    public int updateVideoMonthCount(@Param("videoId") String videoId);

    public int updateVideoYearCount(@Param("videoId") String videoId);
}
