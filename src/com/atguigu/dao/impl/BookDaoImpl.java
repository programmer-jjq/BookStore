package com.atguigu.dao.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.domain.Book;
import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

    // 让 BookDaoImpl实现类继承BaseDao类，实现BookDao接口,重写接口内的方法
    @Override
    public int addBook(Book book) {
        // sql语句
        String sql = "INSERT INTO t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) VALUES(?,?,?,?,?,?)";
        // 调用 BaseDao的 update() 方法
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?;";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id =?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id` ,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgpath from t_book where id=?";
        // 调用 BaseDao的 querySingle() 方法
        Book book = querySingle(Book.class, sql, id);
        return book;
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id` ,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgpath from t_book ";
        // 调用 BaseDao的 queryMulti() 方法
        List<Book> books = queryMulti(Book.class, sql);
        return books;
    }

    /*
    *  以下方法：为前后台及价格搜索分页的功能
    */
    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) querySingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id` ,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgpath from t_book limit ? , ?";
        // 调用 BaseDao的 queryMulti() 方法
        return queryMulti(Book.class, sql, begin, pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) querySingleValue(sql,min,max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select `id` ,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgpath " +
                " from t_book where price between ? and ? order by price limit ? , ?";
        // 调用 BaseDao的 queryMulti() 方法
        return queryMulti(Book.class, sql,min,max,begin, pageSize);
    }
}
