package com.atguigu.book.dao;

import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-31 13:38
 */
public interface CartItemDAO {
    //1.修改特定的购物车项目：
    //
    void updateCartItem(CartItem cartItem);

    //2.新增CartItem
    void addCartItem(CartItem cartItem);

    //3.获取用户的购物车中的所有购物车项
    List<CartItem> getCartItemlist(User user);

    //4.删除指定的CartItem
    void delCartItem(CartItem cartItem);
}
