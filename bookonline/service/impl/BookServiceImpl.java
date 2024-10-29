package com.example.bookonline.service.impl;

import com.example.bookonline.dao.BookDao;
import com.example.bookonline.dao.impl.BookDaoImpl;
import com.example.bookonline.entity.Book;
import com.example.bookonline.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao = new BookDaoImpl();
//    获取图书列表
//    @return
    @Override
    public List<Book> getBooks() {
        return bookDao.selectAll();
    }
//    根据id 获取图书信息
//      @param id id
//      @return
        @Override
        public Book getBookById(int id) {
            return bookDao.getBookById(id);
        }
}
