package com.atguigu.book.pojo;

import java.math.BigDecimal;

/**
 * @author xujian
 * @create 2022-12-30 20:54
 * 购物车CartItem（代表购物车中一条记录，并不是用户的购物车）:
 *  图书、数量、所属用户
 *  id
 *  book
 *  buyCount
 *  userBean
 */
public class CartItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User userBean;

    private double xj;

    public double getXj() {
        BigDecimal price = new BigDecimal(getBook().getPrice() + "");
        BigDecimal count = new BigDecimal(buyCount + "");
        BigDecimal bigDecimalXj = price.multiply(count);
        xj = bigDecimalXj.doubleValue();
        return xj;
    }

    public void setXj(double xj) {
        this.xj = xj;
    }

    public CartItem(){}

    public CartItem(Integer id, Integer buyCount) {
        this.id = id;
        this.buyCount = buyCount;
    }

    public CartItem(Book book, Integer buyCount, User userBean) {
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
    }

    public CartItem(Integer id) {
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

    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(User userBean) {
        this.userBean = userBean;
    }
}
