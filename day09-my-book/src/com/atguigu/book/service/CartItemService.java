package com.atguigu.book.service;

import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-31 14:07
 */
public interface CartItemService {

    //1.向数据库中插入一条新的CartItem
    void addCartItem(CartItem cartItem);

    //2.修改cartItem的buyCount
    void updateCartItem(CartItem cartItem);

    //3.对1和2的总和
    void addOrUpdateCartItem(CartItem cartItem, Cart cart);

    //4.获取固定用户的Cart
    Cart getCart(User user);

    //5.获取cart的cartItem，注意item内部的book属性信息完整
    List<CartItem> getCartItemList(User user);
}
