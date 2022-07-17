package com.example.seigmovies.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Table(name = "tb_chat")
public class Chat implements Serializable {
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
     * 另一个用户id
     */
    @Column(name = "anotherUserId")
    private String anotherUserId;

    /**
     * 视频id
     */
    @Column(name = "videoId")
    private String videoId;

    /**
     * 评论所属id
     */
    @Column(name = "chatId")
    private String chatId;
}
