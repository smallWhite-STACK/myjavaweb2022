package com.atguigu.book.dao;

import com.atguigu.book.pojo.OrderBean;
import com.atguigu.book.pojo.OrderItem;
import com.atguigu.book.pojo.User;

import java.util.List;

/**
 * @author xujian
 * @create 2023-01-01 15:56
 */
public interface OrderItemDAO {
    //增加订单项
    void addOrderItem(OrderItem orderItem);

    //
    List<OrderBean> getOrderBeanByUser(User user);

}
