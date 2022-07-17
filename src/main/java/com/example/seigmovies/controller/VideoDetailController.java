package com.example.seigmovies.controller;

import com.example.seigmovies.entity.Video;
import com.example.seigmovies.service.LastestService;
import com.example.seigmovies.service.VideoDetailService;
import com.example.seigmovies.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@CrossOrigin
public class VideoDetailController {

    @Autowired
    private VideoDetailService videoDetailService;
    @Autowired
    private LastestService lastestService;


    @GetMapping(value = "/videoDetail/index/{videoId}/{videoNumber}")
    public String getVideoDetail(Model model,
                                 @PathVariable("videoId") String videoId,
                                 @PathVariable("videoNumber") String videoNumber) {
        System.out.println(videoId);
        Video video = videoDetailService.selectVideoById(videoId);
        String total = video.getTotal();
        if (total.contains("-")) {
            String[] split = total.split("-");
            total = split[0];
        }
        int totalPages = Integer.parseInt(total);
        System.out.println(video);
        System.out.println("当前第" + videoNumber + "个视频");
        List<Video> lastestVideoList = lastestService.getLastestVideoList();
        int month = videoDetailService.updateVideoMonthCount(videoId);
        int week = videoDetailService.updateVideoWeekCount(videoId);
        int year = videoDetailService.updateVideoYearCount(videoId);
        System.out.println("周：" + week + "月：" + month + "年：" + year);
        model.addAttribute("videoNumber", videoNumber);
        model.addAttribute("lastestList", lastestVideoList);
        model.addAttribute("video", video);
        model.addAttribute("total", totalPages);
        return "videoDetail/index";
    }
}
