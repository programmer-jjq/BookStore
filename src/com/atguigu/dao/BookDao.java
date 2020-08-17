package com.atguigu.dao;

import com.atguigu.domain.Book;
import java.util.List;

public interface BookDao {

    // BookDao接口类，实现了书籍的增删改查业务

    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    /*
    * 以下方法,实现了前后台的分页功能
    */
    public Integer queryForPageTotalCount();

    public List<Book> queryForPageItems(int begin, int pageSize);

    public Integer queryForPageTotalCountByPrice(int min, int max);

    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
