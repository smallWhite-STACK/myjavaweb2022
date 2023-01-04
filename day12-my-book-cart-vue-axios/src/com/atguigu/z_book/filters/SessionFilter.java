package com.atguigu.z_book.filters;

import com.atguigu.book.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xujian
 * @create 2023-01-02 13:06
 */

//@WebFilter("*.do") //拦截所有的*.do请求
//
//
//@WebFilter(urlPatterns = {
//                            "*.do","*.html"
//                         },
//            initParams = {
//                        @WebInitParam(name = "bai",
//                                      value= "/xjbook/page.do?operate=page&page=user/login,/xjbook/user.do?null")
//                                    //value中将会有多个字符串，以“，”分隔，作为白名单对象
//                                    ///xjbook/user.do等同于/xjbook/user.do?null(如果不想写null，则在下方进行字符串拼接时做一个判断，如果为null就不拼接)
//                        }
//            ) //使用{}可以写多个
public class SessionFilter implements Filter {

    List<String> baiList = null;
    @Override
    public void init(FilterConfig config) throws ServletException {
        String bai = config.getInitParameter("bai");
        String[] baiArr = bai.split(",");
        //List为成员变量
        baiList = Arrays.asList(baiArr);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.先看session是否有User对象
            //1.1servletRequest--》HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        System.out.println("RequestURL"+request.getRequestURL());
        System.out.println("RequestURI"+request.getRequestURI());
        System.out.println("RequestQueryString"+request.getQueryString());



        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();
        String str = requestURI+"?"+queryString;  //现在获取的
            //如果str与我们通过初始化参数中获取的List中出现过，就放行
        if(baiList.contains(str)){
            filterChain.doFilter(request, response);
            return;
        }else{

            //1.2获取session和user
            HttpSession session = request.getSession();
            Object currUser = session.getAttribute("currUser");
                //1.3判断user
            if(currUser==null){
                //1.4如果为空，则重新向到login页面
                response.sendRedirect("page.do?operate=page&page=user/login");
            }else{
                //1.5放行
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
