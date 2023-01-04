package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.HostReplyDAO;
import com.atguigu.qqzone.pojo.HostReply;

/**
 * @author xujian
 * @create 2022-12-29 13:50
 */
public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {

    @Override
    public void delHostReply(Integer id) {
        super.executeUpdate("delete from t_host_reply where id = ?", id);
    }

    @Override
    public HostReply getHostReplyByReplyId(Integer reply) {
        String sql = "select * from t_host_reply where reply = ?";
        HostReply load = super.load(sql, reply);
        return load;

    }
}
