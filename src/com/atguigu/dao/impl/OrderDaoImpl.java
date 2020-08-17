package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDao;
import com.atguigu.domain.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

    // 保存订单
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(),order.getUserId());
    }

    // 根据用户Id查询订单信息
    @Override
    public List<Order> queryOrderByUserId(int userId) {
        String sql ="SELECT order_id orderid,create_time createtime,price,STATUS,user_id userid FROM t_order WHERE user_id = ? ";
        return queryMulti(Order.class,sql,userId);
    }

    // 查询所有订单
    @Override
    public List<Order> queryOrders() {
        String sql = "SELECT order_id orderid,create_time createtime,price,STATUS,user_id userid FROM t_order";
        return queryMulti(Order.class, sql);
    }

    // 修改订单状态
    @Override
    public int changeOrderStatus(String orderId,int status) {
        String sql = "UPDATE t_order SET STATUS = ? WHERE order_id = ?";
        return update(sql, status, orderId);
    }
}