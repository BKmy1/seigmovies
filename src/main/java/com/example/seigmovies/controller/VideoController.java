package com.example.seigmovies.controller;

import com.example.seigmovies.entity.PageQo;
import com.example.seigmovies.entity.Video;
import com.example.seigmovies.entity.VideoTime;
import com.example.seigmovies.entity.VideoType;
import com.example.seigmovies.service.TagsService;
import com.example.seigmovies.service.VideoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VideoController {

    @Autowired
    private TagsService tagsService;
    @Autowired
    private VideoService videoService;


    /**
     * 分页
     *
     * @param model
     * @param pageNo
     * @return
     */
    @GetMapping(value = "/video/index/{pageNo}")
    public String getAllVideo(Model model, @PathVariable("pageNo") String pageNo) {
        if (pageNo == null) {
            pageNo = "1";
        }
        System.out.println(pageNo);
        int currentPageNo = Integer.parseInt(pageNo);
        List<Video> videoList = videoService.getVideoList();
        List<VideoType> allVideoTypes = tagsService.getAllVideoTypes();
        List<VideoTime> allVideoTime = tagsService.getAllVideoTime();
        int pageSize = 18;
        int total = videoList.size();
        int totalPage = 0;
        if (total % pageSize == 0) {
            totalPage = total / pageSize;
        } else {
            totalPage = total / pageSize + 1;
        }
        List<Video> tempVideoList = new ArrayList<Video>();
        if (currentPageNo == totalPage) {
            tempVideoList = videoList.subList((currentPageNo - 1) * pageSize, total);
        } else {
            tempVideoList = videoList.subList((currentPageNo - 1) * pageSize, currentPageNo * pageSize);
        }
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("total", total);
        model.addAttribute("currentPageNo", currentPageNo);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("videoTypesList", allVideoTypes);
        model.addAttribute("videoTimeList", allVideoTime);
        model.addAttribute("videoList", tempVideoList);
        return "/video/index";
    }

    /**
     * 按照类型查询
     *
     * @param model
     * @param time
     * @param type
     * @return
     */
    @GetMapping(value = "/video/type/index/{type}")
    public String getVideoType(Model model, String time,
                               @PathVariable("type") String type) {
        List<Video> videoListTypeAndTime = videoService.getVideoListTypeAndTime(time, type);
        List<VideoType> allVideoTypes = tagsService.getAllVideoTypes();
        List<VideoTime> allVideoTime = tagsService.getAllVideoTime();
        model.addAttribute("videoTypesList", allVideoTypes);
        model.addAttribute("videoTimeList", allVideoTime);
        model.addAttribute("dtype",type);
        model.addAttribute("videoListTypeAndTime", videoListTypeAndTime);
        return "/video/type/index";
    }

    /**
     * 按照时间查询
     *
     * @param model
     * @param time
     * @param type
     * @return
     */
    @GetMapping(value = "/video/time/index/{time}")
    public String getVideoTime(Model model, @PathVariable("time") String time,
                               String type) {
        List<Video> videoListTypeAndTime = videoService.getVideoListTypeAndTime(time, type);
        List<VideoType> allVideoTypes = tagsService.getAllVideoTypes();
        List<VideoTime> allVideoTime = tagsService.getAllVideoTime();
        model.addAttribute("videoTypesList", allVideoTypes);
        model.addAttribute("videoTimeList", allVideoTime);
        model.addAttribute("time",time);
        model.addAttribute("videoListTypeAndTime", videoListTypeAndTime);
        return "/video/time/index";
    }

    @PostMapping(value = "/video/select")
    @ResponseBody
    public List<Video> selectVideo(String title) {
        List<Video> videoList = videoService.selectVideoByNameAndActor(title);
        System.out.println("-----------------------");
        System.out.println(videoList);
        return videoList;
    }


    @GetMapping(value = "/video/index/{time}/{type}")
    public String getVideoTypeAndTime(Model model, @PathVariable("type") String type, @PathVariable("time") String time) {
        List<Video> videoListTypeAndTime = videoService.getVideoListTypeAndTime(time, type);
        model.addAttribute("videoListTypeAndTime", videoListTypeAndTime);
        return "/video/index";
    }
}
