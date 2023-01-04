package com.atguigu.book.pojo;

/**
 * @author xujian
 * @create 2022-12-30 20:48
 * 订单详情：OrderItem
 * 图书、数量、所属订单
 *
 * id
 * book
 * buyCount
 * orderBean
 */
public class OrderItem {
    private Integer id;

    //M：1
    private Book book;           //订单详情之   书名
    private Integer buyCount;    //订单详情之   某一本书数量
    //M:1
    private OrderBean orderBean; //订单详情之   此订单详情对应的订单

    public OrderItem(){}

    public OrderItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public OrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }
}
