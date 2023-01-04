package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-28 15:20
 */
public interface  UserBasicService {
    //登录
    UserBasic login(String loginId,String pwd);

    //获取好友列表
    List<UserBasic> getFriendList(UserBasic userBasic);

    //通过id获取用户信息
    UserBasic getUserBasicById(Integer id);
}
