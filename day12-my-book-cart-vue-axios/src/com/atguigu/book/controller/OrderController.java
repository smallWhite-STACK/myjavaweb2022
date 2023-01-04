package com.atguigu.book.controller;

import com.atguigu.book.dao.OrderDAO;
import com.atguigu.book.pojo.OrderBean;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author xujian
 * @create 2023-01-01 16:52
 */
public class OrderController {

    private OrderService orderService;
    /**
     * 结账
     */
    public String checkout(HttpSession session){
         /*
         OrderBean5个（除去id）属性

//         Integer id ;

         Integer orderNo;
         Date orderDate;
         User orderUser;
         Double orderMoney;
         Integer orderStatus;
         */
        OrderBean orderBean = new OrderBean();


        Date date = new Date();

        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDate();
        int hours = date.getHours();
        int minutes = date.getMinutes();
        int seconds = date.getSeconds();

        //1.orderNo
        orderBean.setOrderNo(UUID.randomUUID().toString()+"_"+year+month+day+hours+minutes+seconds);
        //2.orderDate
        orderBean.setOrderDate(date);
        //3.orderUser;
        User currUser = (User) session.getAttribute("currUser");
        orderBean.setOrderUser(currUser);
        //4.orderMoney;(购物车的totalPrice)
        Double totalPrice = currUser.getCart().getTotalPrice();
        orderBean.setOrderMoney(totalPrice);
        //5.orderStatus;(直接默认值)
        orderBean.setOrderStatus(0);


        orderService.addOrderBean(orderBean);

        return "index";

    }

    /**
     * 展示用户的订单
     * ，需要修改cart.html的“我的订单”超链接到CartController
     */
    public String getOrderList(HttpSession session){
        //我们通过session获取User，（User作为OrderBean的属性，可以作为已知数据查询对应的 Order）

        User currUser = (User) session.getAttribute("currUser");
        //需要OrderService
        List<OrderBean> orderBeanByUser = orderService.getOrderBeanByUser(currUser);

        //对于获取的订单列表：orderBeanByUser
                //要么在User中增减一个属性 private List<OrderBean> orderBeanList
                //或者直接将其设置为session的属性
        currUser.setOrderBeanList(orderBeanByUser);

        session.setAttribute("currUser", currUser);
        //转用户订单页面
        return "order/order";
     }
}
