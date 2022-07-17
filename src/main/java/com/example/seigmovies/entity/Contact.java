package com.example.seigmovies.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Table(name = "tb_contact")
public class Contact implements Serializable {
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
     * 联系邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 账户密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 留言内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 上传文件地址
     */
    @Column(name="upload")
    private String upload;

    /**
     * 留言时间
     */
    @Column(name = "publish_time")
    private String publishTime;
}
