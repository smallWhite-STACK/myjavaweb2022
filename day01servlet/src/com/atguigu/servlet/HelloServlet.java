package com.atguigu.servlet;

import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xujian
 * @create 2022-12-15 11:11
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //从请求request中获取参数
        String fname = req.getParameter("fname");
        int price = new Integer(req.getParameter("price"));
        int fcount =new Integer(req.getParameter("fcount"));
        String remark = req.getParameter("remark");

        System.out.println(fname);
        System.out.println(price);
        System.out.println(fcount);
        System.out.println(remark);
        //修改xml文件
        Fruit fruit = new Fruit(0,fname, price, fcount, remark);
        FruitDAOImpl fruitDAO = new FruitDAOImpl();
        boolean b = fruitDAO.addFruit(fruit);
        System.out.println(b?"success":"failed");


    }
    //HttpServlet这个是在tocmat中的jar包
        //如何导入：进入“项目结构”--“模块（javaeweb2022）”--“依赖”--"+"--添加库--“Application server libraries中的tocat”

}
