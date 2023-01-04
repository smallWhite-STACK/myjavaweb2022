package com.atguigu.fruit.servlet;

import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;
import com.atguigu.myssm.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xujian
 * @create 2022-12-21 10:03
 */
@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {
    //思考重写doGet还是doPost方法
//        因为不是表单的形式，所以重写doPost方法
    private FruitDAOImpl fruitDAO = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取fid
        String fid = req.getParameter("fid");
        if(StringUtil.isNotEmpty(fid)){
            int id = Integer.parseInt(fid);
            //2.使用FruitDAO操作数据库
            fruitDAO.delFruitByFid(id);
            //3.需要更新页面
                //思考：使用服务器转发还是重定向
//            super.processTemplate("index", req, resp);
            //使用服务器转发，session中保存的index没有改变。因此需要使用重定向
            resp.sendRedirect("index");
        }
    }

}
