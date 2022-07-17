package com.example.seigmovies.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Table(name = "tb_video_type")
public class VideoType implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型id
     */
    @Column(name = "typeId")
    private String typeId;

    /**
     * 类型
     */
    @Column(name = "type")
    private String type;
}
