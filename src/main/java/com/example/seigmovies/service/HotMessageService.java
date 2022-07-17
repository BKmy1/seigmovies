package com.example.seigmovies.service;

import com.example.seigmovies.entity.HotMessage;

import java.util.List;

public interface HotMessageService {
    public List<HotMessage> selectHotMessagesL();

    public List<HotMessage> selectHotMessagesR();
}
