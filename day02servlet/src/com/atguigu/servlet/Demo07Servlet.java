package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author xujian
 * @create 2022-12-17 20:47
 * 演示从HttpSession保存作用域中获取数据
 * (这里只要都是google浏览器就可以获取到
 * 但是如果设置时用的google,获取是使用edge就不能获取)
 */
public class Demo07Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo07.....");

    }
}
