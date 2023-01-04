package com.atguigu.book.dao;

import com.atguigu.book.pojo.OrderBean;

/**
 * @author xujian
 * @create 2023-01-01 15:55
 */
public interface OrderDAO {
    //增加订单
    void addOrderBean(OrderBean orderBean);

    //
    Integer getOrderTotalBookCount(OrderBean orderBean);
 }
