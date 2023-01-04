package com.atguigu.book.controller;

import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.CartItemService;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author xujian
 * @create 2022-12-31 13:30
 */
public class CartController {
    private CartItemService cartItemService;


    public String addCart(Integer bookId , HttpSession session){
        /*
        * 逻辑：
        *   如果当前购物车中已经存在该书对象，则只修改数量buyCount（+1）
        *   否则，需要在我的购物车中增加一个CartItem(购物车中一条记录)，buyCount=1
        *
        * 因此，我们需要设置CartItemDAO
        *
        * 以上属于Cart逻辑层面的操作，因此service已经实现
        * */

        //由于addOrUpdateCartItem需要参数CartItem和Cart，
            //我认为应该在CartItemService获取CartItem,
                //具体实现应该是以bookId作为参数，先获取
        //老师：通过在登录时就获取用户的购物车
            //1.现在CartItemService中实现一个方法getCart(User user)
            //2.再去CartItemDAO中实现获取用户的所有购物车项getCartItemlist(User user)
            //3.此时我们拥有了用户的Cart对象,
            //4.我们则取login时获取cart，并且为User添加一个属性Cart(每个人都有一个购物车)
            //5.因此需要通过session获取Cart
        User currUser = (User) session.getAttribute("currUser");
        Cart cart = currUser.getCart();
            //6.cartItem是新建的（id book buyCount userBean）
                //CartItem需要设置三个参数的构造函数
                //Book需要设置一个带有id的构造函数
        CartItem cartItem = new CartItem(new Book(bookId),1,currUser);

        cartItemService.addOrUpdateCartItem(cartItem, cart);
        //跳转到哪里：
        return "redirect:cart.do";   //省略了operate=index
                                    //其实还是在CartController，我们需要定义一个index
    }


    /**
     * 加载当前用户的购物车信息
     *  我：
     *      1. 需要通过session获取用户currUser，
     *      2. 然后使用CartItemService获取当前用户的购物车信息cart
     *      3. 设置session的currUser的cart属性，利用thymeleaf使用cart的三个属性
     * @return
     */
    public String index(HttpSession session){
        User currUser = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(currUser);
        currUser.setCart(cart);
        session.setAttribute("currUser", currUser);
        //然后去修改cart.html
        return "cart/cart";
    }

//    operate=editCart&cartItemId='+cartItemId+'&buyCount='+buyCount;
//    public String  editCart(Integer cartItemId,Integer buyCount){
//        //session获取当前用户
//        cartItemService.updateCartItem(new CartItem(cartItemId,buyCount));
//        return "redirect:cart.do";
//    }

    public String  editCart(Integer cartItemId,Integer buyCount){
        //session获取当前用户
        cartItemService.updateCartItem(new CartItem(cartItemId,buyCount));
        return "";
    }


    public String cartInfo(HttpSession session){
        User currUser = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(currUser);
        cart.getTotalPrice();
        cart.getTotalBookCount();
        cart.getTotalCount();
        //我们需要返回cart
        Gson gson = new Gson();
        String cartToJsonStr = gson.toJson(cart);
        return "json:"+cartToJsonStr;
        
    }
}
