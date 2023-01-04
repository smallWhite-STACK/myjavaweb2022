package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-29 13:40
 */
public interface ReplyService {
    //根基topicID获取相关的所有reply
    List<Reply> getReplyListById(Integer topicId);

    //添加回复
    void addReply(Reply reply);

    //删除
    void delReply(Integer id);

    //删除列表
    void delReplayList(Topic topic);
}
