package com.example.seigmovies.mapper;

import com.example.seigmovies.entity.VideoTime;
import com.example.seigmovies.entity.VideoType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagsMapper {

    @Select("select * from tb_video_type")
    public List<VideoType> getAllVideoTypes();

    @Select("select * from tb_video_time")
    public List<VideoTime> getAllVideoTime();
}
