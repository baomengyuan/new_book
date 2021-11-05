package com.swjtu.test;

import com.swjtu.dao.BookDao;
import com.swjtu.dao.impl.BookDaoImpl;
import com.swjtu.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author baomengyuan
 * @create 2021-10-16 14:50
 */
public class BookDaoTest {
    private BookDao bookDao=new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"包孟源","1960",new BigDecimal(999),100000,0,null));
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"遮天","辰东",new BigDecimal(59.9),100000,0,null));
    }

    @Test
    public void queryBookById() {
        Book books = bookDao.queryBookById(21);
        System.out.println(books);
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()){
            System.out.println(queryBook);
        }
    }
}