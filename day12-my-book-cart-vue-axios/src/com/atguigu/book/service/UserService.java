package com.atguigu.book.service;

import com.atguigu.book.dao.UserDAO;
import com.atguigu.book.pojo.User;

/**
 * @author xujian
 * @create 2022-12-31 10:30
 */
public interface UserService {


    public User login(String uname,String pwd);

    public void addUser(User user);
    public User getUsrByUname(String uname);

}
