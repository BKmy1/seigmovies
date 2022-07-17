package com.example.seigmovies.service.impl;

import com.example.seigmovies.entity.Chat;
import com.example.seigmovies.entity.ChatDetail;
import com.example.seigmovies.entity.PageQo;
import com.example.seigmovies.entity.Video;
import com.example.seigmovies.mapper.TopicMapper;
import com.example.seigmovies.service.TopicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public int addChat(Chat chat) {
        return topicMapper.addChat(chat);
    }

    @Override
    public int addChatDetail(ChatDetail chatDetail) {
        return topicMapper.addChatDetail(chatDetail);
    }

    @Override
    public Video selectChatById(String videoId) {
        return topicMapper.selectChatById(videoId);
    }

    @Override
    public List<ChatDetail> selectChatDetail() {
        return topicMapper.selectChatDetail();
    }

    @Override
    public PageInfo<ChatDetail> queryAll(PageQo pageQo) {
        PageHelper.startPage(pageQo.getCurrentPage(),pageQo.getPageSize());
        List<ChatDetail> chatDetailList = topicMapper.selectAll();
        return new PageInfo<>(chatDetailList);
    }

    @Override
    public List<ChatDetail> selectAll() {
        return topicMapper.selectAll();
    }

    @Override
    public int deleteTopic(int chatId) {
        return topicMapper.deleteTopic(chatId);
    }

    @Override
    public int UpdateTopic(ChatDetail chatDetail) {
        return topicMapper.UpdateTopic(chatDetail);
    }

    @Override
    public ChatDetail selectTopicById(int id) {
        return topicMapper.selectTopicById(id);
    }
}
