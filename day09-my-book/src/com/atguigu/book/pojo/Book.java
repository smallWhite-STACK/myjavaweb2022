package com.atguigu.book.pojo;

/**
 * @author xujian
 * @create 2022-12-30 19:43
 * 2.Book图书：
 * 书名、作者、价格、销量、库存、封面、状态
 *
 * id
 * bookImg封面
 * bookName书名
 * price价格   Double
 * author作者
 * saleCount销量
 * bookCount库存
 * bookStatus状态 Integer
 */
public class Book {
    private Integer id;
    private String bookImg;
    private String bookName;
    private Double price;
    private String author;
    private Integer saleCount;
    private Integer  bookCount;
    private Integer bookStatus = 0; //默认状态为0（表示正常） ，-1表示失效（假删除）

    public Book(){}

    public Book(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }
}
