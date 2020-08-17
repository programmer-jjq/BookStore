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

public class ClientBookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    /*
     * @Description : 处理前台分页功能
     * @returns : 返回 page分页对象
    */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2、调用 BookService.page(pageNo,pageSize) 获取 Page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("client/bookServlet?action=page");
        // 3、保存配置对象到 Request域中
        req.setAttribute("page",page);
        // 4、请求转发到 pages/client/index.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    /*
     * @Description : 处理前台价格区间搜索分页功能
     * @returns : 返回 page分页对象
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数 pageNo/pageSize/min/max
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        int max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        // 2、调用 BookService.pageByPrice(pageNo,pageSize,min,max) 获取 Page对象
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);

        // 给分页条的 URL地址参数追加价格区间参数
        // 由于是方法内部，不需要线程安全，使用 StringBulider即可
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        // 如果有最小价格参数，就追加到分页条的地址参数中
        if( req.getParameter("min") != null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        // 如果有最大价格参数，就追加到分页条的地址参数中
        if( req.getParameter("max") != null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        // 3、保存配置对象到 Request域中
        req.setAttribute("page",page);
        // 4、请求转发到 pages/client/index.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
