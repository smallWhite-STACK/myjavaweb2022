package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.HostReply;

/**
 * @author xujian
 * @create 2022-12-29 13:46
 */
public interface HostReplyService {
    HostReply getHostReplyById(Integer replyId);

    //删除
    void delHostReply(Integer id);
}
