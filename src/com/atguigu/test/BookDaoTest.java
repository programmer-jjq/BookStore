package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.domain.Book;
import org.junit.Test;

import java.math.BigDecimal;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        int update = bookDao.addBook(new Book(null, "雪中悍刀行", "烽火戏诸侯", new BigDecimal(500), 10000, 0, null));
        if (update == 1){
            System.out.println("添加图书成功!");
        }else {
            System.out.println("添加图书失败!");
        }
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(6);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(4, "武动乾坤", "天蚕土豆", new BigDecimal(500), 10000, 0, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println( bookDao.queryForPageTotalCount() );
    }

    @Test
    public void queryForPageItems() {
        for (Book book : bookDao.queryForPageItems(0, 4)) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println( bookDao.queryForPageTotalCountByPrice(10,50) );
    }

    @Test
    public void queryForPageItemsByPrice() {
        for (Book book : bookDao.queryForPageItemsByPrice(0, 4,10,50)) {
            System.out.println(book);
        }
    }
}