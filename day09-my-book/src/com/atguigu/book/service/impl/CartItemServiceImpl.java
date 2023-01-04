package com.atguigu.book.service.impl;

import com.atguigu.book.dao.CartItemDAO;
import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.BookService;
import com.atguigu.book.service.CartItemService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xujian
 * @create 2022-12-31 14:10
 */
public class CartItemServiceImpl implements CartItemService {

    private CartItemDAO cartItemDAO;

    private BookService bookService;
    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        List<CartItem> cartItemlist = cartItemDAO.getCartItemlist(user);
        //需要bookService帮助
            //完成bookService通过id后去完整book的功能
        for(CartItem cartItem : cartItemlist){
            Book bookById = bookService.getBookById(cartItem.getBook().getId());
            //重写设置这个cartItem的book
            cartItem.setBook(bookById);
        }
        return cartItemlist;
    }


    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemlist = getCartItemList(user);
        //此时我们获取到用户购物车中所有的购物项，考虑Cart的属性，需要新建Cart
        //1.新建一个map,用于保存所有条目
        Map<Integer, CartItem> cartItemHashMap = new HashMap<>();
        //2.将list加入map
        for(CartItem cartItem:cartItemlist){
            //key是book的id
            cartItemHashMap.put(cartItem.getBook().getId(), cartItem);
        }

        Cart cart = new Cart();
        //获取cart的三个属性
        //1.属性CartItemMap
        cart.setCartItemMap(cartItemHashMap);

        //2.属性TotalCount和3.属性TotalPrice的get方法会自动计算，因此无需操作

        return cart;
        //需要借助dao
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
    }

    /**
     * 将cartItem添加或更新到cart中
     * @param cartItem 购物车项
     * @param cart     用户的购物车
     */
    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        //判断当前用户的购物车中是否有该书
            //有--》UpdateCartItem
            //无-->addCartItem

            //因此此时我们需要增加一个pojo的（用户的购物车类Cart）
//        if(cart!=null){
//            //1.//判断当前用户的购物车中是否有该书
//            if (cart.getCartItemMap().get(cartItem.getBook().getId()) != null) {
//                //有--》UpdateCartItem
//                UpdateCartItem(cartItem);
//            }else{
//                addCartItem(cartItem);
//            }
//        }

        if(cart!=null){
//            我们还应该判断一下cart.getCartItemMap()是否存在
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            //如果购物车中没有关于说明购物车项的map，则我们需要新建一个赋值
            if(cartItemMap==null){
                cartItemMap = new HashMap<>();
            }

            if (cartItemMap.containsKey(cartItem.getBook().getId())) {
                //有--》UpdateCartItem
                //首先需要更新一下cartItem的数量，然后update
                CartItem existCartItem = cartItemMap.get(cartItem.getBook().getId());

                Integer beforeBuyCount = existCartItem.getBuyCount();
                existCartItem.setBuyCount(beforeBuyCount+1);
                updateCartItem(existCartItem);
            }else{
                addCartItem(cartItem);
            }
        }else{
            //如果没有购物车则直接add
            addCartItem(cartItem);
        }
    }
}
