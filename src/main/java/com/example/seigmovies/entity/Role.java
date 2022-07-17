package com.example.seigmovies.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Entity(name = "tb_role")
public class Role implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色类型
     */
    @Column(name = "role")
    private String role;

    /**
     * 角色id 0-管理员，1-用户
     */
    @Column(name = "role_auth")
    private Integer role_auth;
}
