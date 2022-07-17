package com.example.seigmovies.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Entity(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String user_id;

    /**
     * 用户账号
     */
    @Column(name = "account")
    private String account;

    /**
     * 用户密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 用户角色
     */
    @Column(name = "role_id")
    private Integer role_id;

    /**
     * 用户状态 0-正常，1-锁定
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 用户头像地址
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 用户邮箱
     */
    @Column(name = "email")
    private String email;

}
