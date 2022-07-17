package com.example.seigmovies.mapper;

import com.example.seigmovies.entity.Danmuku;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DanmuKuMapper {

    @Select("select * from tb_danmu where videoId = #{videoId} limit #{max}")
    public List<Danmuku> getDanmuByVid(String videoId, int max);

    @Insert("insert into tb_danmu (time,type,colorTen,color,author,text,videoId) " +
            "Values(#{time},#{type},#{colorTen},#{color},#{author},#{text},#{videoId})")
    public int addDanmu(Danmuku danmuku);
}
