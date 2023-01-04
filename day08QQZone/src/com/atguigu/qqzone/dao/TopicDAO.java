package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-28 14:58
 */
public interface TopicDAO {
    //获取指定用户的所有topic
    public List<Topic> getTopicList(UserBasic userBasic);

    //添加日志
    public void add(Topic topic);

    //删除日志
    public void del(Topic topic);

    //获取特定日志信息
    public Topic getTopic(Integer id);
}
