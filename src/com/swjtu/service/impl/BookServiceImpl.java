package com.swjtu.service.impl;

import com.swjtu.dao.BookDao;
import com.swjtu.dao.impl.BookDaoImpl;
import com.swjtu.pojo.Book;
import com.swjtu.pojo.Page;
import com.swjtu.service.BookService;

import java.util.List;

/**
 * @author baomengyuan
 * @create 2021-10-16 15:00
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Integer pageTotalCount=bookDao.queryForPageTotalCount();
        Page<Book> page=new Page<>();
        page.setPageSize(pageSize);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal=pageTotalCount/pageSize;//求总页码
        if(pageTotalCount%pageSize>0) {
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);
        if(pageNo<1){
            pageNo=1;
        }
        if(pageNo>pageTotal) {
            pageNo=pageTotal;
        }
        page.setPageNo(pageNo);
        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> items=bookDao.queryForPageItems(begin,pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Integer pageTotalCount=bookDao.queryForPageTotalCountByPrice(min,max);
        Page<Book> page=new Page<>();
        page.setPageSize(pageSize);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal=pageTotalCount/pageSize;//求总页码
        if(pageTotalCount%pageSize>0) {
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);
        if(pageNo<1){
            pageNo=1;
        }
        if(pageNo>pageTotal) {
            pageNo=pageTotal;
        }
        page.setPageNo(pageNo);
        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> items=bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);
        return page;
    }
}
