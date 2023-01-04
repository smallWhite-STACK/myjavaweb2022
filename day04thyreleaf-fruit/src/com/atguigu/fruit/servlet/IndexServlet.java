package com.atguigu.fruit.servlet;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;
import com.atguigu.myssm.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author xujian
 * @create 2022-12-18 10:53
 */
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**
         * 分页：关于增加分页功能，我们需要一个pageNo的参数，并且需要修改之前getFruitList方法
         */
        Integer pageNo = 1;

        /**
         * 增加关键字查询：keyword参数
         */

        //关键字1：由于doPost会调用doGet,则需要先设置编码
        req.setCharacterEncoding("utf-8");
        //关键字2：上提session
        HttpSession session = req.getSession();
        //关键字3：判断index中是从关键字查询过来的，还是其他操作过来的
            //使用hidden的input  “oper”
            //如果是从关键字查询得来的，则，form表单将会提交，通过input的name获取request的参数
                //如果oper!=null 说明 通过表单的查询按钮点击过来的
                //如果oper是空的，说明 不是通过表单的查询按钮点击过来的
        String oper = req.getParameter("oper");

        //关键字4：初始化keyword
        String keyword= null;

        //关键字5：判断oper是否为空，分情况讨论
            //条件（oper不为空并且等于其默认值“search”）
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            //说明是点击表单查询发送过来的请求,我们从post中获取keyword

            //关键字5.0:注意：
                //此时，pageNo应该还原为1 ， keyword应该从请求参数中获取
            pageNo = 1 ;

            //关键字5.1获取用户提交的keyword
            keyword = req.getParameter("keyword");
            //关键字5.2对keyword进行判断
                //非空则转换为字符串格式
                //若为空则赋值为空字符串,因为在拼接字符串时防止出现"%null%"
            if(StringUtil.isEmpty(keyword)){
                keyword="";
            }
            //关键字5.3为session设置keyword属性
                //原因：因为我们按照关键字搜索后，在进行翻页等操作时，页面的keyword应该都是一致的，所以我们将其加入session中
            session.setAttribute("keyword", keyword);

        }else {
            //关键字6.说明此处不是点击表单查询发送过来的请求（比如点击下面的上一页下一页或者直接在地址栏输入网址）
            //此时keyword应该从session作用域获取（）
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null){
                keyword = (String)keywordObj ;
            }else{
                keyword = "" ;
            }
            //注意这里我们仍然需要分页功能
            //将分页1code上提：从request获取参数pageNo
            String pageNo1 = req.getParameter("pageNo");//这个参数是通过page(JS函数)获取的
            if(StringUtil.isNotEmpty(pageNo1)){
                pageNo = Integer.parseInt(pageNo1);
            }

        }

//        //分页1：从request获取参数pageNo
//        String pageNo1 = req.getParameter("pageNo");//这个参数是通过page(JS函数)获取的
//        if(StringUtil.isNotEmpty(pageNo1)){
//            pageNo = Integer.parseInt(pageNo1);
//
//        }
        //分页2：由于需要设置session的pageOn属性，所以将获取session的操作提上来
//        HttpSession session = req.getSession();

            //更新当前页的值，因为如果点击下一页时，传入的parameter是pageNo+1,但是session还是没有变，这里就相当于更新。
        session.setAttribute("pageNo", pageNo);

        //分页3：此时修改getFruitList()为getFruitList(int pageNo)

        //关键字7.使用重写的getFruitList(keyword , pageNo)和getFruitCount(keyword)
        //1.首先获取fruitList
        FruitDAO fruitDAO = new FruitDAOImpl();
//        List<Fruit> fruitList = fruitDAO.getFruitList(pageNo);
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword,pageNo);
        //2.保存到session作用域
//        HttpSession session = req.getSession();
        session.setAttribute("fruitList", fruitList);

        //分页4：设置session的pageCount(总页数)属性用来处理尾页
        int fruitCount = fruitDAO.getFruitCount(keyword);
//        int fruitCount = fruitDAO.getFruitCount();
            //关于总页数与总条目数之间的数学关系
        int pageCount = (fruitCount+5-1)/5;
        session.setAttribute("pageCount", pageCount);


        //3.逻辑视图名称 对应到 物理视图
        //此处的视图名称是 index
        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        //逻辑视图名称 ：   index
        //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是：      /       index       .html
        super.processTemplate("index",req,resp);
    }
}
