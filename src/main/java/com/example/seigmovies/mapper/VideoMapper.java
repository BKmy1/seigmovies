package com.example.seigmovies.mapper;

import com.example.seigmovies.entity.PageQo;
import com.example.seigmovies.entity.Video;
import com.example.seigmovies.sql.DynamicSQL;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VideoMapper {

    /**
     * 显示全部视频
     *
     * @return
     */
    @Select("select * from tb_video")
    public List<Video> getVideoList();

    @Select("select * from tb_video")
    public PageInfo<Video> queryAll(PageQo pageQo);

    // 搜索视频  按照标题和演员搜索
//    @Select("select * from tb_video v where v.name like concat(#{title},'%','%')" +
//            " or v.name like concat( '%',#{title}, '%')" +
//            " or v.name like concat('%', '%',#{title})" +
//            " or locate(#{actor},v.actors)")
    @Select("select * from tb_video v where locate(#{title},v.name) > 0 or locate(#{title},v.actors) > 0")
    public List<Video> selectVideoByNameAndActor(@Param("title") String title);

    // 按照时间 和 类型查询  二者选择其中一个
    @SelectProvider(type = DynamicSQL.class, method = "selectTypeAndTime")
    public List<Video> getVideoListTypeAndTime(@Param("time") String time, @Param("type") String type);

    @Insert("insert into tb_video (name,description,publishTime,director,actors,tags) " +
            "values(#{name},#{description},#{publishTime},#{director},#{actors},#{tags})")
    public Boolean addVideo(Video video);

    @Delete("delete from tb_video where id=#{id}")
    public int deleteVideo(int id);

    @Update("update tb_video set description=#{description},director=#{director},name=#{name},publishTime=#{publishTime},tags=#{tags},actors=#{actors} where id=#{id}")
    public int updateVideo(Video video);

    @Select("select * from tb_video where id=#{id}")
    public Video selectVideoById(int id);
//    /**
//     * 按时间查询视频
//     */
//    @Select("select t.videoId from tb_video t WHERE locate(#{time}, t.publishTime)")
//    public List<Video> getVideoTimeList(@Param("time") String time);
//
//    /**
//     * 按类型查询视频
//     */
//    @Select("select t.videoId from tb_video t WHERE locate(#{type}, t.tags)")
//    public List<Video> getVideoTypeList(@Param("type") String type);
}
