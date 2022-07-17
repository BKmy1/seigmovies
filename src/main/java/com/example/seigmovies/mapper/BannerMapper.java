package com.example.seigmovies.mapper;

import com.example.seigmovies.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BannerMapper {

    @Select("select * from tb_banner")
    public List<Banner> getBannerList();
}
