package com.atguigu.cookies.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xujian
 * @create 2023-01-02 14:09
 *    1. 创建Cookie对象
 *    2. 在客户端保存Cookie
 *    3. 设置Cookie的有效时长
 *       cookie.setMaxAge(60)  ， 设置cookie的有效时长是60秒
 *       cookie.setDomain(pattern);
 *       cookie.setPath(uri);
 *    4. Cookie的应用：
 *      4-1: 记住用户名和密码十天 setMaxAge(60 * 60 * 24 * 10)
 *      4-2: 十天免登录
 */
//@WebServlet("/cookie01")
//public class CookieServlet01 extends HttpServlet {
//    //重写service
//
//    @Override
//    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        1. 创建Cookie对象(参数：名字，值)
//        Cookie cookie = new Cookie("uname","jim");
//        //2.
//        response.addCookie(cookie);
//        //3.
//        request.getRequestDispatcher("hello01.html").forward(request, response);
//
//    }
//
//}
@WebServlet("/cookie01")
public class CookieServlet01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建一个Cookie对象
        Cookie cookie = new Cookie("uname","jim");
        //2.将这个Cookie对象保存到浏览器端
        response.addCookie(cookie);
        request.getRequestDispatcher("hello01.html").forward(request,response);
        System.out.println("he");

//        C:\Users\dell\AppData\Local\Google\Chrome\User Data\Default\Network\cookie

//        3. 设置Cookie的有效时长
        cookie.setMaxAge(60);//设置cookie的有效时长是60秒
//        记住用户名和密码十天 setMaxAge(60 * 60 * 24 * 10)

        //domain和path一般不设置
//        cookie.setDomain(pattern);
//        cookie.setPath(uri);

    }
}

