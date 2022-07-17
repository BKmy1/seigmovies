package com.example.seigmovies.service;

import com.example.seigmovies.entity.Danmuku;

import java.util.List;

public interface DanmuKuService {

    public List<Danmuku> getDanmuByVid(String videoId, int max);

    public int addDanmu(Danmuku danmuku);
}
