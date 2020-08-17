package com.atguigu.test;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.domain.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDaoTest {

    // 创建 OrderDao对象
    OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        // 调用 OrderDao.saveOrder(Order order) 方法，保存订单
        orderDao.saveOrder(new Order("1234567890", new Date(), new BigDecimal(100), 0, 1));
    }

    @Test
    public void queryOrders() {
        // 调用 OrderDao.queryOrders() 方法，查询所有订单
        List<Order> orders = orderDao.queryOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {
        // 先存入一笔订单
        orderDao.saveOrder(new Order("1234567890", new Date(), new BigDecimal(100), 0, 1));
        // 调用 OrderDao.changeOrderStatus(orderid , status) 方法，修改订单状态
        orderDao.changeOrderStatus("1234567890",1);
    }

    @Test
    public void queryOrderByUserId() {
        // 先存入两笔订单( 1笔是用户id为1的  1笔是用户id为3的)
        orderDao.saveOrder(new Order("1234567890", new Date(), new BigDecimal(100), 0, 1));
        orderDao.saveOrder(new Order("1234567891", new Date(), new BigDecimal(100), 0, 3));
        // 调用 OrderDao.queryOrderByUserId(userId) 方法，根据用户编号1 查询订单
        System.out.println(orderDao.queryOrderByUserId(1));
    }
}