package com.swjtu.dao.impl;

import com.swjtu.dao.BookDao;
import com.swjtu.pojo.Book;

import java.util.List;

/**
 * @author baomengyuan
 * @create 2021-10-16 14:37
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql="select `id` ,`name`,`author`,`price`,`sales`,`stock`,`img_path`imgPath from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    //添加图书
    @Override
    public int addBook(Book book) {
        String sql="insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    //删除图书
    @Override
    public int deleteBookById(Integer id) {
        String sql="Delete from t_book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql="update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql="select `id` ,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where id=?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select `id` ,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql="Select count(*) from t_book";
        Number count= (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min,int max) {
        String sql="Select count(*) from t_book where price between ? and ?";
        Number count= (Number) queryForSingleValue(sql,min,max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql="select `id` ,`name`,`author`,`price`,`sales`,`stock`,`img_path`imgPath from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }
}
