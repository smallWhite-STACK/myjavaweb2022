package com.atguigu.book.service.impl;

import com.atguigu.book.dao.UserDAO;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.UserService;

/**
 * @author xujian
 * @create 2022-12-31 10:36
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;  //xml中配置

    @Override
    public User login(String uname, String pwd) {
        User user =  userDAO.getUser(uname, pwd);
        return user;
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public User getUsrByUname(String uname) {
        User user = userDAO.getUser(uname);
        return user;
    }
}
