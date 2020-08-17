package com.atguigu.dao;

import com.atguigu.domain.Order;

import java.util.List;

public interface OrderDao {

    // 保存订单
    public int saveOrder(Order order);

    // 根据用户编号查询订单信息
    public List<Order> queryOrderByUserId(int userId);

    // 查询所有订单
    public List<Order> queryOrders();

    // 修改订单状态
    public int changeOrderStatus(String orderId,int status);

}
