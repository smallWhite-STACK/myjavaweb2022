package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.CartItemDAO;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-31 13:41
 */
public class CartItemDAOImpl extends BaseDAO<CartItem> implements CartItemDAO {
    @Override
    public void updateCartItem(CartItem cartItem) {
//        通过CartItem中的book的id修改CartItem的属性buyCount
        String sql="update t_cart_item set buyCount = ? where id = ?";
        super.executeUpdate(sql, cartItem.getBuyCount(),cartItem.getId());
    }

    @Override
    public void addCartItem(CartItem cartItem) {
        String sql="insert into t_cart_item values(0,?,?,?)";
        super.executeUpdate(sql,cartItem.getBook().getId(),cartItem.getBuyCount(),cartItem.getUserBean().getId());
    }

    @Override
    public List<CartItem> getCartItemlist(User user) {
        //需要CartItem的单参构造器

        String sql= "select * from t_cart_item where userBean = ?";
        List<CartItem> cartItems = super.executeQuery(sql, user.getId());
        return cartItems;
    }

    @Override
    public void delCartItem(CartItem cartItem) {
        String sql= "delete from t_cart_item where id = ?";
        super.executeUpdate(sql, cartItem.getId());

    }
}
