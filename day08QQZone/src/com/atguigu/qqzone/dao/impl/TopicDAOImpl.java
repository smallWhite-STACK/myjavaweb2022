package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.ReplyDAO;
import com.atguigu.qqzone.dao.TopicDAO;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-28 15:12
 */
public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("select * from t_topic where author = ? " , userBasic.getId());
    }

    @Override
    public void add(Topic topic) {

    }

    @Override
    public void del(Topic topic) {
        super.executeUpdate("delete from t_topic where id=?",topic.getId());

    }

    @Override
    public Topic getTopic(Integer id) {

        return load("select * from t_topic where id = ? ", id);

    }
}