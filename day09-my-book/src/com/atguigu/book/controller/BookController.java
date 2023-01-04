package com.atguigu.book.controller;

import com.atguigu.book.dao.BookDAO;
import com.atguigu.book.pojo.Book;
import com.atguigu.book.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xujian
 * @create 2022-12-31 11:08
 */
public class BookController {
    private BookService bookService;

    //刚才在UserController中说的获取图书列表。我们在BookService实现
    public String index(HttpSession session){
        List<Book> bookList = bookService.getBookList();
        session.setAttribute("bookList", bookList);
        //user在login时转到book.do,当我们设置完session后，我们应该跳转到首页index

        //因为index是在pages目录下（通过之前设置prefix  /WEB-INF/pages/,这里我们直接写index即可）
        return "index";

    }
}
