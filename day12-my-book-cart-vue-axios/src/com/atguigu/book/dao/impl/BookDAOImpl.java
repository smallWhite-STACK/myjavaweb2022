package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.BookDAO;
import com.atguigu.book.pojo.Book;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-31 10:56
 */
public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public List<Book> getBookList() {
        String sql = "select * from t_book where bookStatus = 0";
        List<Book> bookList = super.executeQuery(sql);
        return bookList;
    }

    @Override
    public Book getBookByBookId(Integer id) {
        String sql = "select * from t_book where id=?";
        Book load = super.load(sql, id);
        return load;
    }
}
