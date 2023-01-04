package com.atguigu.qqzone.controllers;

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xujian
 * @create 2022-12-29 12:52
 */

//topic.do对应这个,记得配置xml
public class TopicController {
    private TopicService topicService;

    public String topicDetail(Integer id, HttpSession session) {
        //通过id获取topic的详细信息
        Topic topicById = topicService.getTopicById(id);

        //设置session的topic属性
        session.setAttribute("topic", topicById);
        return "frames/detail"; //detail.html，因为detail外面还有一个文件夹frames
    }

    public String delTopic(Integer topicId) {
        topicService.delTopic(topicId);
        return "redirect:topic.do?operate=getTopicList";
    }

    public String getTopicList(HttpSession session) {

        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        List<Topic> topicList = topicService.getTopicList(userBasic);
        userBasic.setTopicList(topicList);
        session.setAttribute("friend", userBasic);

        return "frames/main";
    }
}
