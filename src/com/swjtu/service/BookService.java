package com.swjtu.service;

import com.swjtu.pojo.Book;
import com.swjtu.pojo.Page;

import java.util.List;

/**
 * @author baomengyuan
 * @create 2021-10-16 14:58
 */
public interface BookService {
    public void addBook(Book book);
    public void deleteBookById(Integer id);
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    Page<Book> page(int pageNo, int pageSize);
    Page<Book> pageByPrice(int pageNo, int pageSize,int min,int max);


}
