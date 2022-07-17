package com.example.seigmovies.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.seigmovies.entity.Danmuku;
import com.example.seigmovies.entity.Video;
import com.example.seigmovies.mq.DelayMqSender;
import com.example.seigmovies.service.DanmuKuService;
import com.example.seigmovies.utils.MapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping(value = "/danmuku")
public class DanmuController {

    @Autowired
    private DanmuKuService danmuKuService;

    @Autowired
    private DelayMqSender mqSender;

    @ResponseBody
    @GetMapping(value = "/v2/")
    public String getDanmuByVid(@RequestParam String id, @RequestParam(value = "max", required = false, defaultValue = "1000") Integer max) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("弹幕视频id：" + id);
        List<Danmuku> data = danmuKuService.getDanmuByVid(id, max);
        List<Object[]> objects = null;
        if (data != null && data.size() != 0) {
            objects = parseDanmukuListToArray(data);
        } else {
            objects = new ArrayList<>();
        }
        map.put("code", 0);
        map.put("data", objects);
//        JSONObject json = new JSONObject(map);
        return MapperUtils.mapToJson(map);
//        return json.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/v2/",method = RequestMethod.POST)
    public String sendDanmu(@RequestBody Map<String,String> param){
        // time、type、color、author、text、videoId
        System.out.println(param);
        Danmuku danmu = new Danmuku();
        danmu.setAuthor(StringUtils.isBlank(param.get("author"))?"":param.get("author"));
        danmu.setText(StringUtils.isBlank(param.get("text"))?"":param.get("text"));
        danmu.setTime(param.get("time")==null?0.0:Double.valueOf(param.get("time")));
        String color=StringUtils.isBlank(param.get("color"))?"#ffffff":param.get("color");
        danmu.setColor(color);
//        danmu.setType(StringUtils.isBlank(param.get("type"))?"right":param.get("type"));
        danmu.setVideoId(StringUtils.isBlank(param.get("id"))?"":param.get("id"));
        danmu.setType("0");
//        danmu.setVideoId(videoId);
        danmu.setColorTen(16777215);
//        danmuKuService.addDanmu(danmu);
        System.out.println("弹幕写入成功！！");

        // 调用 mq 进行延迟消费
        // 分摊1个小时的随机时间，避免大量弹幕入库，非直播视频的弹幕实时性要求不高
        Random random = new Random();
        mqSender.saveDanmuku(danmu,random.nextInt(1000*60));
//        mqSender.saveDanmuku(danmu,random.nextInt(1000*60*60));

        // 返回数据
        Map map = new HashMap();
        map.put("code",0);
        map.put("data",param);
        return MapperUtils.mapToJson(map);
    }

    /**
     * 返回弹幕列表指定格式
     *
     * @param danmukuList
     * @return
     */
    private List<Object[]> parseDanmukuListToArray(List<Danmuku> danmukuList) {
        List<Object[]> data = new ArrayList<>();
        if (danmukuList != null && danmukuList.size() != 0) {
            for (Danmuku dm : danmukuList) {
                Object[] danmu = new Object[]{dm.getTime(), parseTypeToInt(dm.getType()), dm.getColor(), dm.getAuthor(), dm.getText()};
                data.add(danmu);
            }
        }
        return data;
    }

    /**
     * 弹幕位置：0  right， 1 top ， 2 bottom
     *
     * @param type
     * @return
     */
    private int parseTypeToInt(String type) {
        if ("right".equals(type)) {
            return 0;
        }
        if ("top".equals(type)) {
            return 1;
        }
        if ("bottom".equals(type)) {
            return 2;
        }
        return 0;
    }
}
