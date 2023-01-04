package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.TopicDAO;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-28 15:36
 */
public class TopicServiceImpl implements TopicService {
    private TopicDAO topicDAO;  //需要在applicationContext.xml中配置
    private ReplyService replyService;
    private UserBasicService userBasicService;

    @Override
    public void delTopic(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        if(topic!=null){
            //借助replyService需要删除所有的reply
            replyService.delReplayList(topic);
            topicDAO.del(topic);
        }
    }

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        List<Topic> topicList = topicDAO.getTopicList(userBasic);
        return topicList;
    }

    /**
     * getTopic不仅获取到了topic而且获取到了相关的作者信息
     * 因为通过topicDAO获取的topic中的author只有id信息，所以需要借助userBasicService帮助
     * @param id
     * @return
     */
    @Override
    public Topic getTopic(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        UserBasic author = topic.getAuthor();
        author = userBasicService.getUserBasicById(author.getId());

        topic.setAuthor(author);
        return topic;
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = getTopic(id);

        //若是直接返回，则只有topic本身信息，没有reply的信息，
                //因此我们需要考虑reply(而在reply内部又考虑了HostReply)
        List<Reply> replyListById = replyService.getReplyListById(topic.getId());
        //设置topic的属性
        topic.setReplyList(replyListById);
        return topic;
    }
}
