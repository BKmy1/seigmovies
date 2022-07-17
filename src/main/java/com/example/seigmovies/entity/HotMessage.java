package com.example.seigmovies.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Table(name = "tb_hot_message")
public class HotMessage implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    /**
     * 资讯id
     */
    @Column(name="messageId")
    private String messageId;

    /**
     * 资讯标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 资讯地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 资讯发布时间
     */
    @Column(name = "publish")
    private String publish;
}
