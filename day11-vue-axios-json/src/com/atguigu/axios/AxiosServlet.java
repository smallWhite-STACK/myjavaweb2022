package com.atguigu.axios;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xujian
 * @create 2023-01-03 15:20
 */

@WebServlet("/axios01.do")   //axios01.do
public class AxiosServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        System.out.println("uname为"+uname);
        System.out.println("pwd为"+pwd );

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(uname+"_"+pwd);  //在客户端的then中接收

        throw  new NullPointerException("空指针异常");
    }
}
