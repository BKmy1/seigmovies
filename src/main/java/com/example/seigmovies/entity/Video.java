package com.example.seigmovies.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_video")
public class Video implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 视频id
     */
    @Column(name = "videoId")
    private String videoId;

    /**
     * 视频名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 视频地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 视频图片
     */
    @Column(name = "pic")
    private String pic;

    /**
     * 视频缩略图
     */
    @Column(name = "image")
    private String image;

    /**
     * 视频简介
     */
    @Column(name = "description")
    private String description;

    /**
     * 视频发行时间
     */
    @Column(name= "publishTime")
    private String publishTime;

    /**
     * 视频导演
     */
    @Column(name = "director")
    private String director;

    /**
     * 视频演员
     */
    @Column(name = "actors")
    private String actors;

    /**
     * 视频类型 电影 电视剧
     */
    @Column(name = "type")
    private String type;

    /**
     * 视频标签
     */
    @Column(name = "tags")
    private String tags;

    /**
     * 视频状态  正片   更新中
     */
    @Column(name = "status")
    private String status;

    /**
     * 总集数
     */
    @Column(name = "total")
    private String total;

    /**
     * 评分  豆瓣8.9
     */
    @Column(name = "score")
    private Float score;
}
