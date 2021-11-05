package com.swjtu.dao;

import com.swjtu.pojo.Book;

import java.util.List;

/**
 * @author baomengyuan
 * @create 2021-10-16 14:34
 */
public interface BookDao {
    public  List<Book> queryForPageItems(int begin, int pageSize);
    public  int addBook(Book book);
    public int deleteBookById(Integer id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    Integer queryForPageTotalCount();
    Integer queryForPageTotalCountByPrice(int min,int max);
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
