package com.example.seigmovies.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Table(name = "tb_chat_detail")
public class ChatDetail implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "userId")
    private String userId;

    /**
     * 用户内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 消息类型  1-用户消息  2-系统消息
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 发送时间
     */
    @Column(name = "createTime")
    private String createTime;

    /**
     * 回复时间
     */
    @Column(name = "updateTime")
    private String updateTime;

    /**
     * 消息id
     */
    @Column(name = "chatId")
    private String chatId;

    /**
     * 视频id
     */
    @Column(name = "videoId")
    private String videoId;

    /**
     * 视频图片地址
     */
    @Column(name = "images")
    private String image;
}
