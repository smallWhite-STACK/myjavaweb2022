package com.atguigu.book.dao;

import com.atguigu.book.pojo.Book;

import java.util.List;

/**
 * @author xujian
 * @create 2022-12-31 10:53
 */
public interface BookDAO {
    //关于getBookList的参数，如果不考虑分页和关键字(minPrice,maxPrice)查询，我们在这无需参数
    //        但是要是考虑上面两个功能，则需要参数keyword和pageNo
    List<Book> getBookList();

    //id获取book
    Book getBookByBookId(Integer bookId);
}
