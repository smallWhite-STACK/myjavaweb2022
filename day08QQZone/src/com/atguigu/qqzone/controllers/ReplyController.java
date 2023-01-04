package com.atguigu.qqzone.controllers;

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author xujian
 * @create 2022-12-30 9:17
 */
//public class ReplyController  {
//
//    private ReplyService replyService;
//
//
//    /**
//     *
//     * @param content:回复内容
//     * @param topicId:是当前回复对应的标题id(从form表单提交过来的)
//     * @param session
//     * @return
//     */
//    public String addReply(String content, Integer topicId, HttpSession session){
//        //1.需要一个reply，去设置一个Reply带有四个参数的构造函数
//
//        UserBasic userBasic = (UserBasic)session.getAttribute("userBasic");
//        Reply reply = new Reply(content,new Date(),userBasic,new Topic(topicId));
//        replyService.addReply(reply);
//
//        return "redirect:topic.do?operate=topicDetail&id="+topicId;
//    }
public class ReplyController {

    private ReplyService replyService ;

    public String addReply(String content ,Integer topicId , HttpSession session){
        UserBasic author = (UserBasic)session.getAttribute("userBasic");
        Reply reply = new Reply(content , new Date() , author , new Topic(topicId));
        replyService.addReply(reply);
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
        // detail.html
    }
    public String delReply(Integer id,Integer topicId){
        replyService.delReply(id);
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }

}
