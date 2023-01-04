package com.atguigu.qqzone.controllers;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xujian
 * @create 2022-12-28 15:43
 */
public class UserController {
    //根据之前fruit的学习，controller将会调用Service中的方法,controller的返回值都是字符串，然后中心控制器来接受处理


    private UserBasicService userBasicService ;   //需要在applicationContext.xml中配置
    private TopicService topicService;            //需要在applicationContext.xml中配置


    //1.登录功能
    //我们需要登录+跳转
    //      （跳转需要获取：好友列表、日志列表）
    //      (并且获取后需要设置该用户的（好友列表、日志列表）两个属性)
    public String login(String loginId, String pwd, HttpSession session){
        //1.1获取该用户
        UserBasic login = userBasicService.login(loginId, pwd);
        if(login!=null){
            //1.2获取该用户的好友列表
            //List<UserBasic> friendList = login.getFriendList(); //注意UserBasic下的getFriendList查到的对象是id没有详细信息
            List<UserBasic> friendList = userBasicService.getFriendList(login);

            //1.3获取日志列表
            List<Topic> topicList = topicService.getTopicList(login);

            //1.4设置改用户的上面两个属性
            login.setFriendList(friendList);
            login.setTopicList(topicList);

            //1.5需要将该用户保存到session
            session.setAttribute("userBasic", login);
            session.setAttribute("friend", login);
            return "index";
        }else{
            return "login"; //登录失败还是返回到登录页面。
        }
    }

//    根据id获取指定userBasic信息，查询这个userBasic的topicList，然后覆盖session.friend对应的value
    public String friend(Integer id,HttpSession session){
        UserBasic currFriend = userBasicService.getUserBasicById(id);
        //此时我们获取到该朋友的信息，当我们点击他时，超链接获取到他的日志列表
                //  获取用户的日志列表时使用的方法是topicService而不是UserBasic下的get方法
//        List<Topic> currFriendTopicList = currFriend.getTopicList();

        List<Topic> currFriendTopicList = topicService.getTopicList(currFriend);
        //设置currFriend的日志列表属性
        currFriend.setTopicList(currFriendTopicList);
        //设置session.friend
        session.setAttribute("friend", currFriend);

        //返回值：
        return "index";
        //记得去main.index中修改迭代的日志列表

    }

}
