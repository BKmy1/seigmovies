package com.example.seigmovies.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Table(name = "tb_video_detail")
public class VideoDetail implements Serializable {
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
     * 周点击数
     */
    @Column(name = "weekCount")
    private Integer weekCount;

    /**
     * 月点击数
     */
    @Column(name = "monthCount")
    private Integer monthCount;

    /**
     * 年点击数
     */
    @Column(name = "year_count")
    private Integer yearCount;
}
