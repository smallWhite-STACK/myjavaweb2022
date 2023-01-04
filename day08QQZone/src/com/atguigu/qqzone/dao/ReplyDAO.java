package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-28 15:01
 */
public interface ReplyDAO {
    //获取指定日志的回复列表
    public List<Reply> getReplyList(Topic topic);

    //添加回复
    public void add(Reply reply);

    //删除回复
    public void del(Integer id);

    //根据id获取reply
    public Reply getReplyById(Integer id);
}
