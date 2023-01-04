package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.ReplyDAO;
import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.HostReplyService;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-29 13:41
 */
public class ReplyServiceImpl implements ReplyService {
    private ReplyDAO replyDAO;

    //此处引入的是其他POJO对应的Service接口，而不是DAO接口
    //其他POJO对应的业务逻辑是封装在service层的，我需要调用别人的业务逻辑方法，而不要去深入考虑人家内部的细节
    private HostReplyService hostReplyService;

    private UserBasicService userBasicService;
    @Override
    public List<Reply> getReplyListById(Integer topicId) {
        List<Reply> replyList = replyDAO.getReplyList(new Topic(topicId));
        //因为得到所有的reply中包括hostReply所以需要对得到的replyList处理一下

        for (int i = 0; i <replyList.size() ; i++) {
            //取出一个
            Reply reply = replyList.get(i);

            //1.找到reply关联的author
            Integer id = reply.getAuthor().getId();
            UserBasic userBasicById = userBasicService.getUserBasicById(id);
            reply.setAuthor(userBasicById);

            //2.将关联的hostReply设置进来
            HostReply hostReplyById = hostReplyService.getHostReplyById(reply.getId());
            reply.setHostReply(hostReplyById);
        }


        return replyList;
    }

    @Override
    public void addReply(Reply reply) {
        replyDAO.add(reply);
    }

    @Override
    public void delReply(Integer id) {
        //这里不能简单的删除，因为一个回复可能存在主人回复
//        主人回复中的主键是用户回复的属性，因此需要先删除主人回复，再删除用户回复
        //1.根据id获取reply
        Reply replyById = replyDAO.getReplyById(id);
        //2.获取主人回复，删除
        if(replyById!=null){
            //看是否有主人回复
            HostReply hostReplyById = hostReplyService.getHostReplyById(id);
            if(hostReplyById!=null){
                hostReplyService.delHostReply(replyById.getHostReply().getId()); //注意id是hostReply的id
            }
//            if(replyById.getHostReply()!=null){}
        }
        //3.删除回复
        replyDAO.del(id);
    }

    @Override
    public void delReplayList(Topic topic) {

        List<Reply> replyList = replyDAO.getReplyList(topic);

        if(replyList!=null){
            for (Reply reply:replyList) {
                delReply(reply.getId());

            }
        }
    }
}
