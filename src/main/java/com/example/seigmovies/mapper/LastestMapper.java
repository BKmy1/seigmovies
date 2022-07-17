package com.example.seigmovies.mapper;

import com.example.seigmovies.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LastestMapper {
    // 最近更新
    @Select("select * from tb_video where status='更新中' limit 1,6")
    public List<Video> getLastestVideoList();
}
