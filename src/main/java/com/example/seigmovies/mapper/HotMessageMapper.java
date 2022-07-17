package com.example.seigmovies.mapper;

import com.example.seigmovies.entity.HotMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HotMessageMapper {

    @Select("select * from tb_hot_message limit 1,10")
    public List<HotMessage> selectHotMessagesL();

    @Select("select * from tb_hot_message limit 40,10")
    public  List<HotMessage> selectHotMessagesR();
}
