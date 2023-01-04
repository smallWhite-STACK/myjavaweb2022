package com.atguigu.cookies.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author xujian
 * @create 2023-01-02 16:00
 */
@WebServlet("/kaptcha01")
public class KaptchaServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        我们先去方位hello02.html获取验证码图像
//        然后再访问kaptcha01，控制台就会打印

        HttpSession session = req.getSession();
        Object kaptcha_session_key = session.getAttribute("KAPTCHA_SESSION_KEY");
        System.out.println("kaptcha_session_key:  "+kaptcha_session_key);
    }
}
