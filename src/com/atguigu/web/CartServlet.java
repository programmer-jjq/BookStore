package com.atguigu.web;

import com.atguigu.domain.Book;
import com.atguigu.domain.Cart;
import com.atguigu.domain.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{

    //创建一个 BookService对象
    private BookService bookService = new BookServiceImpl();

    // 添加商品到购物车
//    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        // 获取请求的参数 商品编号
//        int id = WebUtils.parseInt(req.getParameter("id"),0);
//        // 调用bookService.queryBookById(id): Book的图书信息
//        Book book = bookService.queryBookById(id);
//        // 把图书信息转换成 CartItem商品项
//        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
//        // 从 Session域中获取 Cart购物车
//        Cart cart = (Cart) req.getSession().getAttribute("cart");
//        // 对 cart购物车进行判断
//        if(cart == null){
//            // 如果 cart购物车为 null,则创建 cart购物车
//            cart = new Cart();
//            req.getSession().setAttribute("cart",cart);
//        }
//        // 调用 Cart.addItem(CartItem); 添加商品项到Cart购物车内
//        cart.addItem(cartItem);
//        // 重定向回 加入购物车请求发起之前的地址
//        // （HTTP协议有一个请求头 Referer，会在请求发起时，浏览器地址栏中的地址发送给服务器）
//        resp.sendRedirect(req.getHeader("Referer"));
//        // 最后一个添加的商品名称
//        req.getSession().setAttribute("lastName",cartItem.getName());
//    }

    // Ajax实现添加商品到购物车
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        // 调用bookService.queryBookById(id): Book的图书信息
        Book book = bookService.queryBookById(id);
        // 把图书信息转换成 CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 从 Session域中获取 Cart购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 对 cart购物车进行判断
        if(cart == null){
            // 如果 cart购物车为 null,则创建 cart购物车
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        // 调用 Cart.addItem(CartItem); 添加商品项到Cart购物车内
        cart.addItem(cartItem);

        req.getSession().setAttribute("lastName",cartItem.getName());

        // 返回购物车总的商品数量和最后一个添加的商品名称
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
        Gson gson = new Gson();
        // 将 resultMap集合转换为Json字符串
        String resultMapJsonString = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJsonString);
    }

    // 从购物车中删除商品
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取请求的参数 商品编号Id
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        // 从 Session域中获取cart购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 对 cart购物车判断
        if(cart !=null){
            // 调用 Cart.deleteItem(Integer id); 根据商品Id,从购物车内删除该商品
            cart.deleteItem(id);
            // 重定向回 购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    // 清空购物车
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1、从 Session域中获取cart购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 2、对 cart购物车判断
        if(cart !=null){
            // 3、调用 Cart.clear(); 清空购物车
            cart.clear();
            // 4、重定向回 购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    // 更新商品数量
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取请求的参数 商品编号Id,商品修改的数量count
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        // 从Session域中获取cart购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 对 cart购物车判断
        if(cart != null){
            //调用 Cart.updateCount(id,count); 修改商品数量
            cart.updateCount(id,count);
            // 重定向回 购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }


}
