package com.example.seigmovies.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author admin
 */
@Data
@ToString
@Table(name = "tb_danmu")
public class Danmuku implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "time")
    private Double time;

    @Column(name = "type")
    private String type;

    @Column(name = "colorTen")
    private Integer colorTen;

    @Column(name = "color")
    private String color;

    @Column(name = "author")
    private String author;

    @Column(name = "text")
    private String text;

    @Column(name = "videoId")
    private String videoId;
}
