

# 工程简介

## 项目技术

> 框架：springboot
>
> 视图技术：thymeleaf
>
> 数据库实现：mybatis
>
> 缓存：redis
>
> 任务管理：邮箱
>
> 消息队列：rabbitMQ
>
> 权限管理：springsecurity
>
> 评论系统：LeanCloud
>
> 导出功能：easyexcel
>
> 视频播放器：DPlayer2 |  [Powerful danmaku video player | NPlayer](https://nplayer.js.org/)(可以尝试一下)


## 项目功能

### 页面应用

- 首页
  - 图片缩放
  - 媒体查询(窗口动态改变排版布局)
- 全部视频
  - google视频播放无声音
- 排行榜
- 联系我们
- 历史记录
  - 问题：在当前页，跳转当前页的历史记录

- 搜索
  - id选择器优于class

- 登录
- 注册

### 首页

- 数据流
  - 轮播图
  
  - 热门推荐
  
  - 最近更新
  
  - 精品推荐
  
  - 热门资讯
  
  - 历史记录
  
    ```html
    htitle = "<a class='history-info-title' href='http://localhost:8080/videoDetail/index/"+video.videoId+"'>"+video.name+"</a>";
    
    ------ thymeleaf无法进行渲染。
    hgrade = "<a class='history-info-grade' th:href=\"@{\'/videoDetail/index/\'"+video.videoId+"}\">"+video.status+"</a>";
    ```
    
    



### 全部视频

- 数据流

  - 年份
  - 类型
  - 分页

  

  ![movie](F:\日常笔记\github图床\movie.png)

  - ![movie1](F:\日常笔记\github图床\movie1.png)

  

  

![movie2](F:\日常笔记\github图床\movie2.png)

- 视频弹幕功能：

  - https://blog.csdn.net/AA777_/article/details/124085422

  - https://blog.csdn.net/ZPeng_CSDN/article/details/106721975

  - 搭建弹幕接口(无效果)----->决定手写弹幕服务器

  - https://www.iqi360.com/p/dplayer_danmu/docs/5f97d930dc15613aed7480fe

  - [Dplayer播放器弹幕服务器API搭建教程，带公开可用接口_moewang的博客-CSDN博客_弹幕服务器地址](https://blog.csdn.net/moewang/article/details/109318662)

  - 测试弹幕接口（DPlayer.js会默认在请求后添加v3）

    - https://dplayer.moerats.com/v3/?id=9E2E3368B56CDBB4
    - 真实地址是：api: 'https://dplayer.moerats.com/'
    - 在DPlayer.js文件中删除v3数据  ---》 "?id="

    - 返回值类型
      - code: 请求成功
      - data: 
        - time: 弹幕显示的时间
        - type: 弹幕的类型
        - color: 弹幕的颜色
        - author: 弹幕的用户
        - context: 弹幕内容
      - 发送弹幕
      - ![image-20220717110809548](F:\日常笔记\github图床\image-20220717110809548.png)
      - 返回弹幕列表
      - ![image-20220717111143035](F:\日常笔记\github图床\image-20220717111143035.png)
      - 发送弹幕
      - ![image-20220717111357197](F:\日常笔记\github图床\image-20220717111357197.png)

### 排行榜

- 数据流------------根据用户每一次点击来增加数量
  - 周排行榜
  - 月排行榜
  - 年排行榜

### 我的话题

- 数据流
  - 回复我的
  - 我回复的
  - 我的消息
  - 系统通知

> 用户头像： http://124.220.158.140/images/avatar.png
>
> 评论id：objectId
>
> 回复id：pid
>
> 界面：video.videoId

[数据存储开发指南 · Java - LeanCloud 文档](https://leancloud.cn/docs/leanstorage_guide-java.html#hash787692837)

> 评论内容：comment
>
> 评论时间：insertedAt
>
> 评论ip：ip
>
> link：评论页码（video.videoId）
>
> mail: 邮箱
>
> nick：用户名
>
> pid：回复id

- 添加功能
  - 前台评论
  - 评论数据设置ACL为True，方便进行对**leancloud**数据操作。
  - 数据写入后台
  - 数据库读取leanCloud云端数据
  - 写入数据库
  - 不重复添加（**没有实现**）

- 修改功能

- 删除功能
  - 要将ACL的write权限改为：true



### 联系我们

- 数据流
  - 邮箱账号
  - 登录秘密
  - 留言
  - 上传文件
    - 邮箱发送附件，必须开启true
    - 并且把邮件名称传入过去，否则无法显示内容
    - 异步发送

https://blog.csdn.net/weixin_30262255/article/details/96369868

https://blog.csdn.net/arick112/article/details/107865210

### 后台页面

> 后台整体框架基于X-admin下

- backVideo
  - index 首页
  - talk-list  评论数据分页
  - video-list  视频数据分页
  - welcome  欢迎页面(x-admin自带页面)
- 导出功能
  - https://www.cnblogs.com/honggege/p/15993756.html
  - https://blog.csdn.net/royal1235/article/details/123169529
  - https://www.codenong.com/cs110833648/


## 数据库设计

> 用户表，权限表，视频表，视频详情表，评论表，

[sql:locate 函数_mob60475703f08d的技术博客_51CTO博客](https://blog.51cto.com/u_15127681/4208977)

[Mybatis 注解形式多条件查询_YyCarry的博客-CSDN博客_mybatis注解条件查询](https://blog.csdn.net/YyCarry/article/details/78585426)

- 出现以下报错，可能会是数据库多个表中的数据存在同名情况

> Hibernate: select user0_.id as id1_1_, user0_.account as account2_1_, user0_

```mysql
select v.videoId from tb_video v where EXISTS(select vt.type from tb_video_type vt in v.tags);
```

```mysql
select t.tags from tb_video t inner join tb_video_type v on locate("", v.type) > 0
```

```MYSQL
select t.videoId from tb_video t WHERE locate("剧情", t.tags) (locate("剧情", t.tags) AND locate("2022", t.publishTime))
```



# 延伸阅读



### 遇到问题

- th:text无效，无法显示数据
  - 尽量数据库中表的字段名不要与css样式出现重名
- springsecurity不起作用
  - 不允许数据库中存在字段名相同，否则识别不到权限。
- thymeleaf使用EasyExcel出现getOutputStream() has already been called for this response
  - [Springboot thymeleaf 异常：getOutputStream() has already been called for this response 解决 - Java天堂 (javatt.com)](https://www.javatt.com/p/134895)
