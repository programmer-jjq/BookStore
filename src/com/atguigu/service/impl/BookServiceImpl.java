package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.domain.Book;
import com.atguigu.domain.Page;
import com.atguigu.service.BookService;
import java.util.List;

// 创建 BookServiceImpl 接口实现类
public class BookServiceImpl implements BookService {

    // 创建 BookDao对象，调用其中的方法
    private BookDao bookDao = new BookDaoImpl();

    // 添加书籍的功能，调用 BookDao的addBook() 方法
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    // 根据书籍ID删除书籍的功能
    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    // 更新书籍的功能
    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    // 根据书籍ID查询书籍的功能
    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    // 查询所有书籍的功能
    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    // 图书前后台分页的功能
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);

        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        // 设置总记录数
        page.setPageTotalcount(pageTotalCount);

        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if ( pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    // 图书前台价格区间分页的功能
    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);

        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        // 设置总记录数
        page.setPageTotalcount(pageTotalCount);

        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if ( pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }
}
