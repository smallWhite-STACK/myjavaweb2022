package com.atguigu.book.pojo;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-30 19:39
 * 1.User用户类:
 *  用户名（String）、密码(String)、邮箱(String)、角色(role)
 *  id(Integer)
 */
public class User {

    //属性名按照数据库的来设置名字
    private Integer id;
    private String uname;
    private String pwd;
    private String email;
    private Integer role;

    private Cart cart;

    private List<OrderBean> orderBeanList;

    public List<OrderBean> getOrderBeanList() {
        return orderBeanList;
    }

    public void setOrderBeanList(List<OrderBean> orderBeanList) {
        this.orderBeanList = orderBeanList;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public User(){}

    public User(Integer id) {
        this.id = id;
    }

    public User(String uname, String pwd, String email) {
        this.uname = uname;
        this.pwd = pwd;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
