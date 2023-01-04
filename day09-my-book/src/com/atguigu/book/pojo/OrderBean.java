package com.atguigu.book.pojo;


import java.util.Date;
import java.util.List;

/**
 * @author xujian
 * @create 2022-12-30 20:45
 * 订单OrderBean：对应数据库t_order
 *
 * 订单编号、订单日期、订单金额、订单状态、用户
 *
 *
 * id
 * orderNo订单编号
 * orderDate订单日期
 * orderUser用户
 * orderMoney订单金额
 * orderStatus订单状态
 */
public class OrderBean {
    private Integer id ;
    private String orderNo;
    private Date orderDate;
    private User orderUser;
    private Double orderMoney;
    private Integer orderStatus;

    //因为一个订单orderBean可以包含多个订单详情orderItem
    private List<OrderItem> orderItemList;

    private Integer totalBookCount;

    public void setTotalBookCount(Integer totalBookCount) {
        this.totalBookCount = totalBookCount;
    }

    public Integer getTotalBookCount() {
        return totalBookCount;
    }

    public OrderBean(){}

    public OrderBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public User getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(User orderUser) {
        this.orderUser = orderUser;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
