package com.example.seigmovies.service;

import com.example.seigmovies.entity.Chat;
import com.example.seigmovies.entity.ChatDetail;
import com.example.seigmovies.entity.PageQo;
import com.example.seigmovies.entity.Video;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TopicService {

    public int addChat(Chat chat);

    public int addChatDetail(ChatDetail chatDetail);

    // 删除操作

    public Video selectChatById(String videoId);

    public List<ChatDetail> selectChatDetail();

    public PageInfo<ChatDetail> queryAll(PageQo pageQo);

    public List<ChatDetail> selectAll();

    public int deleteTopic(int chatId);

    public int UpdateTopic(ChatDetail chatDetail);

    public ChatDetail selectTopicById(int id);
}
