package com.example.seigmovies.service;

import com.example.seigmovies.entity.PageQo;
import com.example.seigmovies.entity.Video;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoService {

    public List<Video> getVideoList();

    // 按条件和时间查询
    public List<Video> getVideoListTypeAndTime(@Param("time") String time,@Param("type") String type);

    // 搜索视频
    public List<Video> selectVideoByNameAndActor(@Param("title") String title);

    public PageInfo<Video> queryAll(PageQo pageQo);

    public Boolean addVideo(Video video);

    public int deleteVideo(int id);

    public int updateVideo(Video video);

    public Video selectVideoById(int id);

    // 按时间类型查询
//    public List<Video> getVideoTimeList(@Param("time") String time);

    // 按标签类型查询
//    public List<Video> getVideoTypeList(@Param("type") String type);
}
