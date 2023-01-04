package com.atguigu.book.controller;

import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.BookService;
import com.atguigu.book.service.CartItemService;
import com.atguigu.book.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xujian
 * @create 2022-12-31 10:26
 */
public class UserController {
    private UserService userService;
//    private BookService bookService;

    private CartItemService cartItemService;


    /**
     * 这个login需要与input（hidden）的name的value一致
     */
    public String login(String uname, String pwd, HttpSession session){
        //因为需要涉及到DAO，此时需要先设置dao的UserDAOs
        User currUser = userService.login(uname, pwd);
//        System.out.println(currUser.getUname()); //应该可以获取到uname
        //如果登录成功，则显示首页列表，因此我们还需要一个bookList,此时需要去写一个BookDAO,然后设置对应的service
        //获取bookList,然后设置到session中即可
            //但是这个方法是login，我们不使用这个方法

        //因此：分析
        /**
         * 如果获取的currUser不为空，我们仅仅只将currUser设置到session中去，然后重定向到book.do通过中心控制器去寻找bookController
         *
         */
        if (currUser != null) {

            //由于Cart参与，因此我们需要在login时获取该用户的购物车
            Cart cart = cartItemService.getCart(currUser);
            //并且我们在User类中加入属性Cart
            currUser.setCart(cart);
            session.setAttribute("currUser", currUser);
            return "redirect:book.do";  //其实后面省略了index，因为bookController的获取bookList的方法名为index
        }
        //登录失败,回去login页面
        return "user/login";
    }


    public String regist(String verifyCode, String uname, String pwd, String email , HttpSession session, HttpServletResponse response) throws IOException {
        //上面的参数名字在html中对应标签必须拥有name属性且相同

        Object kaptcha_session_key = session.getAttribute("KAPTCHA_SESSION_KEY");

        if (kaptcha_session_key == null || !verifyCode.equals(kaptcha_session_key)) {
            //重新注册

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            //out.println("<script language='javascript'>alert('验证码不正确！');window.location.href='page.do?operate=page&page=user/regist';</script>");
            out.println("<script language='javascript'>alert('验证码不正确！');</script>");
            //return "user/regist";
            return "user/regist";
        }else{
            if(verifyCode.equals(kaptcha_session_key)){
                userService.addUser(new User(uname,pwd,email));
                //去登录
                return "user/login";
            }
        }


        return "user/login";

    }

    public String ckUname(String uname){
        //我们需要一个通过uname获取User的方法（）

        User usrByUname = userService.getUsrByUname(uname);
        //判断该用户在数据库是否存在
        if (usrByUname != null) {
            //名字被占用
            return "json:{'uname':'1'}";
//            return "ajax:1";

        }else{
            //名字可以使用
            return "json:{'uname':'0'}";
//            return "ajax:0";
        }
    }
}
