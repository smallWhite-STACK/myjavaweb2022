package com.atguigu.book.service.impl;

import com.atguigu.book.dao.CartItemDAO;
import com.atguigu.book.dao.OrderDAO;
import com.atguigu.book.dao.OrderItemDAO;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.OrderBean;
import com.atguigu.book.pojo.OrderItem;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.OrderService;

import java.util.List;
import java.util.Map;

/**
 * @author xujian
 * @create 2023-01-01 16:08
 */
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private CartItemDAO cartItemDAO;
    /**
     * 一个用户的订单order（其内部有多条订单orderItem）
     * @param orderBean
     */
    @Override
    public void addOrderBean(OrderBean orderBean) {
/*      1.订单表先添加一条记录
        2.订单详情表添加7条记录
        3.购物车项中删除原来的7条记录*/
        //1.  指定第一步后，orderBean的id是有值的，因此我们使用dao的返回值设置了orderBean的id属性
        orderDAO.addOrderBean(orderBean);

        //2.
                //由于使用orderBean.getOrderItemList()获取的orderItem是null,
                //因此此处我们应该根据用户的购物车项去转换一个一个的订单项
//        List<OrderItem> orderItemList = orderBean.getOrderItemList();
//        //使用OrderItemDAO帮忙
//        for (OrderItem orderItem: orderItemList){
//            orderItemDAO.addOrderItem(orderItem);
//        }
        User currUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = currUser.getCart().getCartItemMap();
        for(CartItem cartItem: cartItemMap.values()){
            //新建一个OderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
        }

        //3.
        for(CartItem cartItem: cartItemMap.values()){
            cartItemDAO.delCartItem(cartItem);

        }


    }

    @Override
    public List<OrderBean> getOrderBeanByUser(User user) {

        List<OrderBean> orderBeanByUser = orderItemDAO.getOrderBeanByUser(user);
        for(OrderBean orderBean : orderBeanByUser){
            Integer orderTotalBookCount = orderDAO.getOrderTotalBookCount(orderBean);
            orderBean.setTotalBookCount(orderTotalBookCount);
        }

        return orderBeanByUser;
    }
}
