package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.ReplyDAO;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-29 13:58
 */
public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {
    @Override
    public List<Reply> getReplyList(Topic topic) {

        String sql = "select * from t_reply where topic = ? ";
        return super.executeQuery(sql, topic.getId());
    }

    @Override
    public void add(Reply reply) {
        String sql = "insert into t_reply values(0,?,?,?,?)";
        super.executeUpdate(sql, reply.getContent(),reply.getReplyDate(),reply.getAuthor().getId(),reply.getTopic().getId());

    }

    @Override
    public void del(Integer id) {
        super.executeUpdate("delete from t_reply where id = ?",id);
    }

    @Override
    public Reply getReplyById(Integer id) {
        Reply load = super.load("select * from t_reply where id = ?",id);
        return  load;
    }
}
