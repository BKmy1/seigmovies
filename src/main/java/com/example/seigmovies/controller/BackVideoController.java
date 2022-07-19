package com.example.seigmovies.controller;

import com.example.seigmovies.entity.ChatDetail;
import com.example.seigmovies.entity.Danmuku;
import com.example.seigmovies.entity.PageQo;
import com.example.seigmovies.entity.Video;
import com.example.seigmovies.service.DanmuKuService;
import com.example.seigmovies.service.TopicService;
import com.example.seigmovies.service.VideoService;
import com.example.seigmovies.utils.LCOUtil;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
@RequestMapping("/backVideo")
public class BackVideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private DanmuKuService danmuKuService;

    @GetMapping("/videoList")
    public String stuList(Model model, PageQo pageQo) {
        if (pageQo.getCurrentPage() == null) {
            pageQo.setCurrentPage(1);
        }
        PageInfo<Video> videoList = videoService.queryAll(pageQo);
        model.addAttribute("pageQo", pageQo);
        model.addAttribute("videoList", videoList);
        return "backVideo/video-list";
    }

    @RequestMapping("/topicList")
    public String awards(Model model, PageQo pageQo) {
        if (pageQo.getCurrentPage() == null) {
            pageQo.setCurrentPage(1);
        }
        PageInfo<ChatDetail> topics = topicService.queryAll(pageQo);
        model.addAttribute("pageQo", pageQo);
        model.addAttribute("topics", topics);
        return "backVideo/talk-list";
    }

    @RequestMapping("/danmuList")
    public String danmuList(Model model, PageQo pageQo){
        if (pageQo.getCurrentPage() == null){
            pageQo.setCurrentPage(1);
        }
        PageInfo<Danmuku> danmus = danmuKuService.selectAll(pageQo);
        model.addAttribute("pageQo",pageQo);
        model.addAttribute("danmus",danmus);
        return "backVideo/danmu-list";
    }

    @GetMapping("/add")
    public String toAdd(@ModelAttribute(value = "video") Video video) {
        return "backVideo/video-add";
    }

    @PostMapping("/addVideo")
    @ResponseBody
    public boolean addVideo(@Validated @ModelAttribute(value = "video") Video video) {
        videoService.addVideo(video);
        return true;
    }

    @RequestMapping("/delete")
    public String deleteStu(int id) {
        int i = videoService.deleteVideo(id);
        System.out.println("删除成功" + i);
        return "redirect:/backVideo/videoList";
    }

    @RequestMapping("/deleteTopic")
    public String deleteTopic(int id) {
        LCOUtil lcoUtil = new LCOUtil();
        ChatDetail chatDetail = topicService.selectTopicById(id);
        int i1 = lcoUtil.deleteTopic(chatDetail.getChatId());
        int i = topicService.deleteTopic(id);
        System.out.println(i1);
        System.out.println("删除成功"+id);
        return "redirect:/backVideo/topicList";
    }

    // 修改数据  传id
    @GetMapping("/updateById")
    public String updateVid(Model model, int id, @ModelAttribute(value = "video") Video video) throws ParseException {
        Video vi = videoService.selectVideoById(id);
        model.addAttribute("vi", vi);
        return "backVideo/video-update";
    }

    // ajax返回成功类型  boolean  用responsebody接收
    @PostMapping("/updateVideo")
    @ResponseBody
    public boolean updateVideo(@ModelAttribute(value = "video") Video video) throws ParseException {
        int i = videoService.updateVideo(video);
        System.out.println("更新成功！！");
        return true;
    }

    // 修改数据  传id
    @GetMapping("/updateTById")
    public String updateTid(Model model, int id, @ModelAttribute(value = "chatDetail") ChatDetail chatDetail) throws ParseException {
        ChatDetail cd = topicService.selectTopicById(id);
        model.addAttribute("cd", cd);
        return "backVideo/talk-update";
    }

    @PostMapping("/updateTopic")
    @ResponseBody
    public boolean updateTopic(@Validated @ModelAttribute(value = "chatDetail") ChatDetail chatDetail) throws ParseException {
        int i = topicService.UpdateTopic(chatDetail);
        LCOUtil lcoUtil = new LCOUtil();
        System.out.println(chatDetail.getChatId());
        int i1 = lcoUtil.UpdateTopic(chatDetail.getChatId(), chatDetail);
        System.out.println(i1);
        System.out.println("修改成功" + i);
        return true;
    }

}
