package com.atguigu.web;

import com.atguigu.domain.Cart;
import com.atguigu.domain.Order;
import com.atguigu.domain.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClientOrderServlet extends BaseServlet{

    private OrderService orderService = new OrderServiceImpl();

    //结账和生成订单功能实现
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取 Cart购物车对象
        Cart cart  = (Cart) req.getSession().getAttribute("cart");
        // 获取 loginUser用户对象
        User loginUser = (User) req.getSession().getAttribute("user");

        if(loginUser == null){
            // loginUser为空，证明没有登录,请求重定向到登录页面
            resp.sendRedirect(req.getContextPath()+"/pages/user/login.jsp");
            return;
        }
        // 获取 userId
        Integer userId = loginUser.getId();
        // 调用 orderService.createOrder(Cart,userId); 生成订单
        String orderId = orderService.createOrder(cart, userId);
        // 将 订单号 orderId 传入Request域中，便于获取
        // req.setAttribute("orderId",orderId);
        // 请求转发到 /pages/cart/checkedout.jsp页面
        // req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);

        // 防止表单重复提交，使用请求重定向跳转页面 (由于是请求重定向，orderId不能保存在 Request域，应该保存到 Session域中 )
        req.getSession().setAttribute("orderId",orderId);
        // 请求重定向到 /pages/cart/checkedout.jsp页面
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    // 根据用户Id查询订单
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1、先获取Session域中的User用户对象
        User user = (User) req.getSession().getAttribute("user");
        // 2、对 User对象进行判断
        if(user == null){
            //如果为空，证明是未登录状态，页面重定向到登录login.jsp页面，再次进行查询操作
            resp.sendRedirect(req.getContextPath()+"/pages/user/login.jsp");
            return;
        }
        // 如果不为空，证明已登录，继续进行查询操作
        // 3、调用 OrderService.showMyOrders(userId); 根据Id查询订单
        List<Order> orders = orderService.showMyOrders(user.getId());
        // 4、将 orders订单存入Request域中
        req.setAttribute("myorders",orders);
        // 5、请求转发到 /pages/order/order.jsp页面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    // 用户签收订单
    protected void receivedOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1、获取签收订单的orderId订单编号
        String orderId = req.getParameter("orderId");
        // 2、调用 orderService.receivedOrder(orderId)方法，签收订单Id为orderId的订单
        orderService.receivedOrder(orderId);
        // 3、重定向到订单页面
        resp.sendRedirect(req.getHeader("referer"));
    }
}
