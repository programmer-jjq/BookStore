package com.atguigu.web;

import com.atguigu.domain.Order;
import com.atguigu.domain.OrderItem;
import com.atguigu.domain.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManagerOrderServlet extends BaseServlet{

    private OrderService orderService = new OrderServiceImpl();

    // 查询所有订单
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1、先获取Session域中的User用户对象
        User user = (User) req.getSession().getAttribute("user");
        // 2、对 User对象进行判断
        if(user == null){
            //如果为空，证明是未登录状态，页面重定向到登录login.jsp页面，再次进行查询操作
            resp.sendRedirect(req.getContextPath()+"/pages/user/login.jsp");
            return;
        }
        // 如果不为空，证明已登录，继续进行查询全部订单操作
        // 3、调用 OrderService.showAllOrders(); 查询全部订单
        List<Order> orders = orderService.showAllOrders();
        // 4、将 orders订单存入Request域中
        req.setAttribute("orders",orders);
        // 5、请求转发到 /pages/order/order_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    };

    // 管理员确认发货(修改订单状态为1)
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取发货订单的orderId订单编号
        String orderId = req.getParameter("orderId");
        // 2、调用 orderService.sendOrder(orderId)方法，将订单编号为 orderId的订单发货
        orderService.sendOrder(orderId);
        // 3、重定向到订单页面
        resp.sendRedirect(req.getHeader("referer"));
    };

    // 查看订单详情
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取订单的orderId订单编号
        String orderId = req.getParameter("orderId");
        // 2、调用 orderService.showOrderDetail(orderId)方法，显示订单编号为 orderId的 订单详情
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        // 3、将 orderItems订单项传入 Request域中
        req.setAttribute("orderItems",orderItems);
        // 4、请求转发到 /pages/order/order_detail.jsp 订单详情页面
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req,resp);
    };
}
