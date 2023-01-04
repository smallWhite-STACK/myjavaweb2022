package com.atguigu.book.service.impl;

import com.atguigu.book.dao.BookDAO;
import com.atguigu.book.pojo.Book;
import com.atguigu.book.service.BookService;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-31 10:58
 */
public class BookServiceImpl  implements BookService {
    private BookDAO bookDAO;
    @Override
    public List<Book> getBookList() {
        List<Book> bookList = bookDAO.getBookList();
        return bookList;
    }

    @Override
    public Book getBookById(Integer bookId) {
        //完成DAO中通过id获取book的方法
        Book bookByBookId = bookDAO.getBookByBookId(bookId);
        return bookByBookId;
    }
}
