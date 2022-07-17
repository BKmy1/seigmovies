package com.example.seigmovies.mapper;

import com.example.seigmovies.entity.Video;
import com.example.seigmovies.entity.VideoDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RankMapper{

    //周排行
    @Select("select name,status,v.videoId from tb_video v join" +
            " tb_video_detail vd on v.videoId = vd.videoId order by weekCount ")
    public List<Video> getWeekRankList();


    //月排行
    @Select("select name,status,v.videoId from tb_video v join" +
            " tb_video_detail vd on v.videoId = vd.videoId order by monthCount ")
    public List<Video> getMonthRankList();


    //年排行
    @Select("select name,status,v.videoId from tb_video v join" +
            " tb_video_detail vd on v.videoId = vd.videoId order by yearCount ")
    public List<Video> getYearRankList();
}
