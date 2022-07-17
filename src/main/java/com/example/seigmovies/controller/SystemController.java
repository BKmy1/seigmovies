package com.example.seigmovies.controller;

import com.example.seigmovies.entity.Banner;
import com.example.seigmovies.entity.HotMessage;
import com.example.seigmovies.entity.Video;
import com.example.seigmovies.entity.VideoDetail;
import com.example.seigmovies.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SystemController {

    @Autowired
    private HotMessageService hotMessageService;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private LastestService lastestService;
    @Autowired
    private HotReommendService hotReommendService;
    @Autowired
    private BestService bestService;

    /**
     * 获取热门资讯、获取轮播图数据
     *
     * @param model
     * @return
     */
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        List<HotMessage> hotMessagesL = hotMessageService.selectHotMessagesL();
        List<HotMessage> hotMessagesR = hotMessageService.selectHotMessagesR();
        List<Banner> bannerList = bannerService.getBannerList();
        List<Video> lastestVideoList = lastestService.getLastestVideoList();
        List<Video> hotVideoList = hotReommendService.getHotVideoList();
        List<Video> bestVideo = bestService.getBestVideo();
        model.addAttribute("lastestList", lastestVideoList);
        model.addAttribute("bannerList", bannerList);
        model.addAttribute("hotMessagesL", hotMessagesL);
        model.addAttribute("hotMessagesR", hotMessagesR);
        model.addAttribute("hotVideoList",hotVideoList);
        model.addAttribute("bestVideoList",bestVideo);
        return "index";
    }
}
