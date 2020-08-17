package com.atguigu.service;

import com.atguigu.domain.Cart;
import com.atguigu.domain.Order;
import com.atguigu.domain.OrderItem;

import java.util.List;

public interface OrderService {

    // 生成订单
    public String createOrder(Cart cart,Integer UserId);

    // 查看我的订单
    public List<Order> showMyOrders(int userId);

    // 查询全部订单
    public List<Order> showAllOrders();

    // 查看订单详情
    public List<OrderItem> showOrderDetail(String orderId);

    // 确认发货(修改订单状态为1)
    public void sendOrder(String orderId);

    // 用户签收订单(修改订单状态为2)
    public void receivedOrder(String orderId);
}
