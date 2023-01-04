package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.HostReply;

/**
 * @author xujian
 * @create 2022-12-28 15:03
 */
public interface HostReplyDAO {
    //根据replyId查询关联的主人回复实体
    HostReply getHostReplyByReplyId(Integer replyId);

    //删除
    void delHostReply(Integer id);
}
