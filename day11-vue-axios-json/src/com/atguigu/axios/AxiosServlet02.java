package com.atguigu.axios;

import com.atguigu.pojo.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xujian
 * @create 2023-01-03 15:20
 */

@WebServlet("/axios02.do")   //axios01.do
public class AxiosServlet02 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader reader = request.getReader();
        StringBuffer stringBuffer = new StringBuffer();
        String str=null;
        while((str=reader.readLine())!=null){
            stringBuffer.append(str);
        }
        System.out.println(stringBuffer.toString()); //{"uname":"lina","pwd":"ok"}

        //因为我们想让获取的json转换为java对象，这里我们新建一个User

        Gson gson = new Gson();
//        GsonBuilder gsonBuilder = new GsonBuilder();
        User user = gson.fromJson(stringBuffer.toString(), User.class);
        System.out.println(user);


        user.setUname("userToJSON");
        String userToJson = gson.toJson(user);
//        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(userToJson);  //去客户端的then对数据进行解析
    }
}
