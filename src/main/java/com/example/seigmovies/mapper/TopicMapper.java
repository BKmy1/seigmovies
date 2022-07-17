package com.example.seigmovies.mapper;

import com.example.seigmovies.entity.Chat;
import com.example.seigmovies.entity.ChatDetail;
import com.example.seigmovies.entity.PageQo;
import com.example.seigmovies.entity.Video;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TopicMapper {

    @Select("select * from tb_chat_detail")
    public PageInfo<ChatDetail> queryAll(PageQo pageQo);

    @Select("select * from tb_chat_detail")
    public List<ChatDetail> selectAll();

    @Delete("delete from tb_chat_detail where id=#{id}")
    public int deleteTopic(int id);

    @Update("update tb_chat_detail set content=#{content},createTime=#{createTime},userId=#{userId},chatId=#{chatId} where id=#{id}")
    public int UpdateTopic(ChatDetail chatDetail);

    @Select("select * from tb_chat_detail where id=#{id}")
    public ChatDetail selectTopicById(int id);

    @Insert("insert into tb_chat (userId,anotherUserId,videoId,chatId) values(#{userId},#{anotherUserId},#{videoId},#{chatId})")
    public int addChat(Chat chat);

    @Insert("insert into tb_chat_detail (userId,content,type,createTime,updateTime,chatId,videoId,image) " +
            "values(#{userId},#{content},#{type},#{createTime},#{updateTime},#{chatId},#{videoId},#{image})")
    public int addChatDetail(ChatDetail chatDetail);

    @Select("select * from tb_video where videoId = #{videoId}")
    public Video selectChatById(String videoId);

    // 当pid存在时，返回查询结果
    @Select("select * from tb_chat_detail cd left join tb_chat c on cd.chatId = c.anotherUserId")
    public List<ChatDetail> selectChatDetail();
}
