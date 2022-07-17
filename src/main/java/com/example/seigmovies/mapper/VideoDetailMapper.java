package com.example.seigmovies.mapper;

import com.example.seigmovies.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface VideoDetailMapper {

    // 根据videoId 查询视频详情
    @Select("select * from tb_video where videoId = #{videoId}")
    public Video selectVideoById(@Param("videoId") String videoId);

    // 视频点击后数据加一
    // 按周添加
    @Update("update tb_video_detail SET weekCount = weekCount + 1 where videoId = #{videoId}")
    public int updateVideoWeekCount(@Param("videoId") String videoId);


    @Update("update tb_video_detail SET monthCount = monthCount + 1 where videoId = #{videoId}")
    public int updateVideoMonthCount(@Param("videoId") String videoId);


    @Update("update tb_video_detail SET yearCount = yearCount + 1 where videoId = #{videoId}")
    public int updateVideoYearCount(@Param("videoId") String videoId);
}
