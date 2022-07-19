package com.example.seigmovies.service.impl;

import com.example.seigmovies.entity.Danmuku;
import com.example.seigmovies.entity.PageQo;
import com.example.seigmovies.mapper.DanmuKuMapper;
import com.example.seigmovies.service.DanmuKuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanmuServiceImpl implements DanmuKuService {

    @Autowired
    private DanmuKuMapper danmuKuMapper;

    @Override
    public List<Danmuku> getDanmuByVid(String videoId, int max) {
        return danmuKuMapper.getDanmuByVid(videoId,max);
    }

    @Override
    public int addDanmu(Danmuku danmuku) {
        return danmuKuMapper.addDanmu(danmuku);
    }

    @Override
    public PageInfo<Danmuku> selectAll(PageQo pageQo) {
        PageHelper.startPage(pageQo.getCurrentPage(),pageQo.getPageSize());
        List<Danmuku> danmukus = danmuKuMapper.selectAll();
        return new PageInfo<>(danmukus);
    }
}
