package com.atguigu.web;

import com.atguigu.domain.Book;
import com.atguigu.domain.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    // 处理分页功能
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2、调用 BookService.page(pageNo,pageSize) 获取 Page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        // 3、保存配置对象到 Request域中
        req.setAttribute("page",page);
        // 4、请求转发到 pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    // 添加图书的业务
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        // 1、获取请求的参数，封装成 Book对象
        // 使用 WebUtils 工具类的 copyParamToBean() 方法，直接将获取的参数注入到 Book对象返回
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        // 2、调用 BookService的 addBook() 方法添加图书
        bookService.addBook(book);
        // 3、跳转到图书列表页面

        // 使用 请求转发进行跳转回出现 BUG
        //req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);

        // 需要使用 请求重定向进行跳转 到图书分页页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    // 删除图书的业务
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数id
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        // 2、调用 BookService的 deleteBookById() 方法删除图书
        bookService.deleteBookById(id);
        // 3、 重定向回图书分页页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    // 修改图书前，先回显修改的图书信息
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数 图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 2、调用 BookService 的 queryBookById()方法查询图书
        Book book = bookService.queryBookById(id);
        // 3、保存图书信息到 Reuqest域中
        req.setAttribute("book",book);
        // 4、请求转发到 book_edit.jsp页面 回显图书信息
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    // 保存修改图书的业务
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数，封装成为 Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 2、调用 BookService 的updateBook() 方法修改图书
        bookService.updateBook(book);
        // 3、请求重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    // 查看全部图书的业务
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、通过 BookService的 queryBooks() 方法查询全部图书
        List<Book> books = bookService.queryBooks();
        // 2、把全部图书保存到 Request域中
        req.setAttribute("books",books);
        // 3、请求转发到 /pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
