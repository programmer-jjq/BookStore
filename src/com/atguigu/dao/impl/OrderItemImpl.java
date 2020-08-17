package com.atguigu.dao.impl;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.domain.OrderItem;

import java.util.List;

public class OrderItemImpl extends BaseDao<OrderItem> implements OrderItemDao {

    // 保存订单项
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    // 根据订单号查询订单明细
    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "SELECT id,NAME,COUNT,price,total_price totalprice,order_id orderid FROM t_order_item WHERE order_id= ?";
        return queryMulti(OrderItem.class,sql,orderId);
    }
}