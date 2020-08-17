package com.atguigu.test;

import com.atguigu.domain.Book;
import com.atguigu.domain.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        // 调用 BookService实现类的 addBook() 方法，添加图书
        bookService.addBook(new Book(null,"斗破苍穹","唐家三少",new BigDecimal(400),400,200,null));;
    }

    @Test
    public void deleteBookById() {
        //// 调用 BookService实现类的 deleteBookById() 方法，删除图书
        bookService.deleteBookById(19);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(7,"重生之大企业家","未知",new BigDecimal(400),400,200,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,10,50));
    }
}