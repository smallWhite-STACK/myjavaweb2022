package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.UserDAO;
import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.User;
import com.atguigu.myssm.basedao.BaseDAO;

/**
 * @author xujian
 * @create 2022-12-31 10:32
 */
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(String uname, String pwd) {

        String sql = "select * from t_user where uname like ? and pwd like ?";
        User load = super.load(sql, uname, pwd);
        return  load;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO t_user values(0,?,?,?,0)"; //用户角色默认是0
        super.executeUpdate(sql, user.getUname(),user.getPwd(),user.getEmail());
    }

    @Override
    public User getUser(String uname) {
        String sql = "select * from t_user where uname=?";
        User user = super.load(sql, uname);
        return user;
    }
}
