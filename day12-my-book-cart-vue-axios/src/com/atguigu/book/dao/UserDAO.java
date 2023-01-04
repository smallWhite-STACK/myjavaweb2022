package com.atguigu.book.dao;

import com.atguigu.book.pojo.User;

/**
 * @author xujian
 * @create 2022-12-31 10:31
 */
public interface UserDAO {

    User getUser(String uname,String pwd);

    //增加用户
    void addUser(User user);

    //通过uname获取User
    User getUser(String uname);
}
