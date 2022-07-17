package com.example.seigmovies.controller;

import com.example.seigmovies.entity.Chat;
import com.example.seigmovies.entity.ChatDetail;
import com.example.seigmovies.entity.Video;
import com.example.seigmovies.service.TopicService;
import com.example.seigmovies.utils.LCOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping(value = "/topic/index")
    public String getTopicIndex(Model model) {
        LCOUtil lcoUtil = new LCOUtil();
//        List<Chat> batchTalkChat = lcoUtil.getBatchTalkChat();
//        List<ChatDetail> batchTalkChatDetail = lcoUtil.getBatchTalkChatDetail();
        System.out.println(lcoUtil.getIsCFirst());
        if (lcoUtil.getIsCFirst()) {
            List<Chat> batchTalkChat = lcoUtil.getBatchTalkChat();
            List<ChatDetail> batchTalkChatDetail = lcoUtil.getBatchTalkChatDetail();
            System.out.println(batchTalkChatDetail.toString());
            for (Chat chat : batchTalkChat) {
                topicService.addChat(chat);
            }
            for (ChatDetail chatDetail : batchTalkChatDetail) {
                String videoId = chatDetail.getVideoId();
                System.out.println("视频id：" + videoId);
                Video video = topicService.selectChatById(videoId);
                chatDetail.setImage(video.getImage());
                topicService.addChatDetail(chatDetail);
            }
            model.addAttribute("chatDetailList", batchTalkChatDetail);
            List<ChatDetail> chatDetailList = topicService.selectChatDetail();
            model.addAttribute("chatPidDetailList", chatDetailList);
            System.out.println("写入成功数据");
        }
//        batchTalkChatDetail.forEach(i -> topicService.addChatDetail(i));
//        model.addAttribute("chatDetailList", batchTalkChatDetail);
//        List<ChatDetail> chatDetailList = topicService.selectChatDetail();
//        model.addAttribute("chatPidDetailList", chatDetailList);
//        System.out.println("写入成功数据");
        return "topic/index";
    }

}
