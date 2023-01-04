package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author xujian
 * @create 2022-12-17 18:45
 * 关于servlet继承关系问题
 * 以及会话跟踪技术说明
 */
public class Demo03Servlet extends HttpServlet { //记得模块的依赖加上tocmat
    //ctrl+H查看类的继承关系


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println(session.getId());
    }
}
