package com.example.seigmovies.service.impl;

import com.example.seigmovies.entity.HotMessage;
import com.example.seigmovies.mapper.HotMessageMapper;
import com.example.seigmovies.service.HotMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotMessageServiceImpl implements HotMessageService {

    @Autowired
    private HotMessageMapper hotMessageMapper;

    @Override
    public List<HotMessage> selectHotMessagesL() {
        return hotMessageMapper.selectHotMessagesL();
    }

    @Override
    public List<HotMessage> selectHotMessagesR() {
        return hotMessageMapper.selectHotMessagesR();
    }
}
