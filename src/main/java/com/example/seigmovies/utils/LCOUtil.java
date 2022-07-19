package com.example.seigmovies.utils;

import cn.leancloud.*;
import cn.leancloud.callback.LCCallback;
import cn.leancloud.core.LeanCloud;
import cn.leancloud.session.LCConnectionManager;
import cn.leancloud.types.LCNull;
import com.example.seigmovies.entity.Chat;
import com.example.seigmovies.entity.ChatDetail;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LCOUtil {

    public static final String appId = "";
    public static final String appKey = "";
    public static final String serverUrl = "";
    // 评论第一次插入
    private boolean isCFirst = true;
    // 评论详情第一次插入
    private boolean isCDFirst = true;
    private List<Chat> chatList;
    private List<ChatDetail> cdList;
    private Integer len;

    public void setIsCFirst(boolean isCFirst) {
        this.isCFirst = isCFirst;
    }

    public void setIsCDFirst(boolean isCDFirst) {
        this.isCDFirst = isCDFirst;
    }

    public boolean getIsCDFirst() {
        return isCDFirst;
    }

    public boolean getIsCFirst() {
        return isCFirst;
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    /**
     * 开启leancloud连接
     */
    static {
        LeanCloud.initialize(appId, appKey, serverUrl);
        LCConnectionManager.getInstance().startConnection(new LCCallback() {
            @Override
            protected void internalDone0(Object o, LCException e) {
                if (e == null) {
                    System.out.println("成功建立webSocke连接");
                } else {
                    System.out.println("建立失败" + e.getMessage());
                }
            }
        });
        // 设置全局的对话事件处理 handler
//        LCIMMessageManager.setConversationEventHandler(new CustomConversationEventHandler());
        // 设置全局的消息处理 handler
//        LCIMMessageManager.registerDefaultMessageHandler(new CustomMessageHandler());
    }

    /**
     * 批量查询所有评论数据
     *
     * @return
     */
    public List<Chat> getBatchTalkChat() {
        LCQuery<LCObject> query = new LCQuery<LCObject>("Comment");
        // 获取此类的所有对象
        query.findInBackground().subscribe(new Observer<List<LCObject>>() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@NonNull List<LCObject> lcObjects) {
//                lcObjects.forEach(i -> System.out.println(i.getString("mail")+i.getString("url")+i.getString("pid")+i.getCreatedAt()+i.getObjectId()+i.getString("nick")+i.getString("comment")));
//                lcObjects.forEach(i -> System.out.println(i));
//                queryList.addAll(lcObjects);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                chatList = new ArrayList<>();
                // 新建一个ACL实例
                LCACL acl = new LCACL();
                acl.setPublicWriteAccess(true);
                for (LCObject l : lcObjects) {
                    l.setACL(acl);
                    l.saveInBackground();
                    Chat chat = new Chat();
                    chat.setVideoId(l.getString("url")); // 视频id
                    chat.setUserId(l.getString("nick")); // 评论名称
                    chat.setAnotherUserId(l.getString("pid")); // 回复id
                    chat.setChatId(l.getObjectId()); // 评论id
                    chatList.add(chat);
                }
                isCFirst = false;
            }

            @Override
            public void onError(@NonNull Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
        return chatList;
    }

    /**
     * 批量查询所有评论详情
     *
     * @return
     */
    public List<ChatDetail> getBatchTalkChatDetail() {
        LCQuery<LCObject> query = new LCQuery<LCObject>("Comment");
        // 获取此类的所有对象
        query.findInBackground().subscribe(new Observer<List<LCObject>>() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@NonNull List<LCObject> lcObjects) {
//                lcObjects.forEach(i -> System.out.println(i.getString("mail")+i.getString("url")+i.getString("pid")+i.getCreatedAt()+i.getObjectId()+i.getString("nick")+i.getString("comment")));
//                lcObjects.forEach(i -> System.out.println(i));
//                queryList.addAll(lcObjects);
                len = lcObjects.size();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                cdList = new ArrayList<>();
                // 新建一个ACL实例
                LCACL acl = new LCACL();
                acl.setPublicWriteAccess(true);
                for (LCObject l : lcObjects) {
                    l.setACL(acl);
                    l.saveInBackground();
                    ChatDetail cd = new ChatDetail();
                    cd.setContent(l.getString("comment"));
                    cd.setCreateTime(sdf.format(l.getCreatedAt()));
                    cd.setUserId(l.getString("nick"));
                    cd.setChatId(l.getObjectId());
                    cd.setType(0);
                    cd.setUpdateTime(sdf.format(l.getUpdatedAt()));
                    cd.setVideoId(l.getString("url"));
                    cdList.add(cd);
                }
                isCDFirst = false;
            }

            @Override
            public void onError(@NonNull Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
        return cdList;
    }

    /**
     * 更新所有数据
     */
    public int UpdateTopic(String objectId, ChatDetail chatDetail) throws ParseException {
        LCObject topic = LCObject.createWithoutData("Comment", objectId);
        System.out.println("objectID:" + objectId);
        String chatId = chatDetail.getChatId();
        String content = chatDetail.getContent();
        String createTime = chatDetail.getCreateTime();
        Date insertedAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
        // 用户姓名
        String userId = chatDetail.getUserId();
        topic.setObjectId(chatId);
        topic.put("comment", content);
        topic.put("insertedAt", insertedAt);
        topic.put("nick", userId);
        topic.put("QQAvatar", "");
        topic.put("url",chatDetail.getVideoId());
        topic.save();
        topic.saveInBackground().subscribe(new Observer<LCObject>() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@NonNull LCObject lcObject) {
                System.out.println("保存成功");
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
        return 0;
    }

    /**
     * 删除评论
     */
    public int deleteTopic(String objectId) {
        LCObject comment = LCObject.createWithoutData("Comment", objectId);
        comment.deleteInBackground().subscribe(new Observer<LCNull>() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@NonNull LCNull lcNull) {
                System.out.println("删除成功！");
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println("错误异常" + throwable.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
        return 0;
    }

//    public static void main(String[] args) {
//        // 参数：objectId：单独一个消息id
//        // comment：评论内容
//        // insertedAt：评论时间
//        // ip：评论ip
//        // link：评论页码地址（长度有限制）
//        // mail：邮箱
//        // nick：用户名
//        // pid or rid  为回复的objectId
//        LCQuery<LCObject> query = new LCQuery<LCObject>("Comment");
//        LCQuery<LCObject> lcObjectLCQuery = query.selectKeys(Arrays.asList("objectId", "nick", "mail", "ip", "comment", "insertedAt", "pid"));
//        query.getFirstInBackground().subscribe(new Observer<LCObject>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable disposable) {
//            }
//
//            @Override
//            public void onNext(@NonNull LCObject lcObject) {
//                String objectId = lcObject.getString("objectId");
//                String nick = lcObject.getString("nick");
//                String mail = lcObject.getString("mail");
//                String ip = lcObject.getString("ip");
//                String comment = lcObject.getString("comment");
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                Date createdAt = lcObject.getCreatedAt();
//                String format = simpleDateFormat.format(createdAt);
//                String pid = lcObject.getString("pid");
//                System.out.println(objectId + "," + nick + "," + mail + "," + ip + "," + comment + "," + format + "," + pid);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable throwable) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//    }
}
