package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.OrderItemDAO;
import com.atguigu.book.pojo.OrderBean;
import com.atguigu.book.pojo.OrderItem;
import com.atguigu.book.pojo.User;
import com.atguigu.myssm.basedao.BaseDAO;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

/**
 * @author xujian
 * @create 2023-01-01 16:01
 */
public class OrderItemDAOImpl extends BaseDAO<OrderBean> implements OrderItemDAO {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql= "insert into t_order_item values(0,?,?,?)";
        super.executeUpdate(sql, orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrderBean().getId());

    }

    @Override
    public List<OrderBean> getOrderBeanByUser(User user) {
        String sql="select * from t_order where orderUser = ?";
        List<OrderBean> orderBeans = super.executeQuery(sql, user.getId());
        return orderBeans;
    }
}
