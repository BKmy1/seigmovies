package com.example.seigmovies.controller;

import com.example.seigmovies.entity.Video;
import com.example.seigmovies.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RankController {

    @Autowired
    private RankService rankService;
    
    @GetMapping(value = "/rank/index")
    public String getRankIndex(Model model) {
        List<Video> weekRankList = rankService.getWeekRankList();
        List<Video> monthRankList = rankService.getMonthRankList();
        List<Video> yearRankList = rankService.getYearRankList();
        model.addAttribute("weekList",weekRankList);
        model.addAttribute("monthList",monthRankList);
        model.addAttribute("yearList",yearRankList);
        return "rank/index";
    }
}
