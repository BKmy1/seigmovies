package com.example.seigmovies.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageQo {
    //分页
    private Integer currentPage=1;    //当前页码,默认显示第一页
    private Integer pageSize =5;       //每页显示的数据量
}
