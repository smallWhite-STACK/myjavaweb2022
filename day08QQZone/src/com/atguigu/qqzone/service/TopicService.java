package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-28 15:35
 */
public interface TopicService {
    //获取该用户的topic--日志列表
    List<Topic> getTopicList(UserBasic userBasic);

    //通过id获取日志的详细信息
    Topic getTopicById(Integer id);

    Topic getTopic(Integer id);

    void delTopic(Integer id);
}
