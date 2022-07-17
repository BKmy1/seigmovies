package com.example.seigmovies.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Table(name = "tb_video_time")
public class VideoTime implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 时间id
     */
    @Column(name = "timeId")
    private String timeId;

    /**
     * 时间
     */
    @Column(name = "time")
    private String time;
}
