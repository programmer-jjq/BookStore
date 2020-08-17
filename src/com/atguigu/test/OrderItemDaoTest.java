package com.atguigu.test;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemImpl;
import com.atguigu.domain.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDaoTest {

    // 创建 OrderItemDao对象
    OrderItemDao orderItemDao = new OrderItemImpl();

    @Test
    public void saveOrderItem() {
        // 调用 OrderItemDao.saveOrderItem(OrderItem orderItem) 方法，保存订单项
        orderItemDao.saveOrderItem(new OrderItem(null,"Java从入门到精通",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"数据结构与算法",1,new BigDecimal(200),new BigDecimal(200),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Netty入门",1,new BigDecimal(300),new BigDecimal(300),"1234567890"));
    }

    @Test
    public void queryOrderItemsByOrderId() {
        // 调用 OrderItemDao.queryOrderItemsByOrderId(orderId) 方法，根据订单编号查询订单
        System.out.println(orderItemDao.queryOrderItemsByOrderId("1234567890"));
    }
}