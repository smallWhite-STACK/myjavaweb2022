package com.atguigu.book.service;

import com.atguigu.book.pojo.Book;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-31 10:58
 */
public interface BookService {

    //获取所有图书list
    List<Book> getBookList();

    //通过id获取book
    Book getBookById(Integer bookId);
}
