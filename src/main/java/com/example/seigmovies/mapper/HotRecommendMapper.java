package com.example.seigmovies.mapper;

import com.example.seigmovies.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HotRecommendMapper {
    // 热门推荐
    @Select("select * from tb_video where score>8.0 limit 1,6")
    public List<Video> getHotVideoList();
}
