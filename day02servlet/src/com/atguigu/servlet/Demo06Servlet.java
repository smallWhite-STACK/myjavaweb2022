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
 * 演示`服务器内部转发`以及`客户端重定向`
 *
 */
public class Demo06Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.服务器内部转发
//        req.getRequestDispatcher("demo07").forward(req, resp);
        //2.客户端重定向
        resp.sendRedirect("demo07");
    }
}
