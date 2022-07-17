package com.example.seigmovies.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Table(name = "tb_banner")
public class Banner implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 轮播图id
     */
    @Column(name = "bannerId")
    private String bannerId;

    /**
     * 轮播图标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 轮播图图片
     */
    @Column(name = "image")
    private String image;

    /**
     * 轮播图地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 序号
     */
    @Column(name = "serialNum")
    private Integer serialNum;

    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "createMan")
    private String createMan;

    /**
     * 修改时间
     */
    @Column(name = "modifyTime")
    private Date modifyTime;

    /**
     * 修改人
     */
    @Column(name = "modifyMan")
    private String modifyMan;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
}
