package com.example.seigmovies.mapper;

import com.example.seigmovies.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BestMapper {

    // 查询精品推荐
    @Select("select * from tb_video where videoId in" +
            "(select videoId from tb_video_detail where yearCount > 1500) limit 1,6")
    public List<Video> getBestVideo();
}
