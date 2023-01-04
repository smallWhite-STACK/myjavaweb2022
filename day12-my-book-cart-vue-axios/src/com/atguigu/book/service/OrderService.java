package com.atguigu.book.service;

import com.atguigu.book.pojo.OrderBean;
import com.atguigu.book.pojo.User;

import java.util.List;

/**
 * @author xujian
 * @create 2023-01-01 16:06
 */
public interface OrderService {
    void addOrderBean(OrderBean orderBean);

    List<OrderBean> getOrderBeanByUser(User user);


}
