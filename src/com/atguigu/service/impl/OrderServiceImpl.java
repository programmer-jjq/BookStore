package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemImpl;
import com.atguigu.domain.*;
import com.atguigu.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    // 创建 OrderDao对象和 OrderItemDao对象
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemImpl();
    // 创建 BookDao对象
    private BookDao bookDao = new BookDaoImpl();

    // 生成订单
    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 订单号 === 唯一性 ()
        String orderId = System.currentTimeMillis()+""+userId;
        // 先有订单，再有订单项
        // 创建一个 Order订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        // 调用 OrderDao.saveOrder(order)，保存订单
        orderDao.saveOrder(order);

        // int i = 12 / 0;

        // 遍历 cart购物车中每一个商品项转换成订单项保存到数据库
        for(Map.Entry<Integer,CartItem>entry : cart.getItems().entrySet()){
            // 获取每一个商品项
            CartItem cartItem = entry.getValue();
            // 将商品项转换为订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            // 保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            // 每保存一个订单项，都要更改对应Book图书的库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        // 生成订单后，情况购物车
        cart.clear();

        return orderId;
    }

    // 根据用户编号查询订单信息
    @Override
    public List<Order> showMyOrders(int userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    // 查询全部订单
    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    // 根据订单号查询订单明细
    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }

    // 管理员确认发货(修改订单状态为1)
    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,1);
    }

    // 用户签收订单(修改订单状态为2)
    @Override
    public void receivedOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,2);
    }
}
