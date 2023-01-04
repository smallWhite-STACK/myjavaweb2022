package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-28 13:21
 */
public interface UserBasicDAO {
    //1.根据账号+密码获取账户信息
    public UserBasic getUserBasic(String loginId,String pwd);
    //2.获取指定用户的所有好友列表
    public List<UserBasic> getUserBasicList(UserBasic userBasic);
    //3.通过id查询UserBasic
    public UserBasic getUserBasicById(Integer id);
}
