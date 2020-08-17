package com.atguigu.test;

import com.atguigu.domain.Cart;
import com.atguigu.domain.CartItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderServiceTest {

    // 创建一个 OrderService类
    OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        // 先创建一个 Cart购物车
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"java从入门到精通",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构算法",1,new BigDecimal(1000),new BigDecimal(1000)));

        // 调用 OrderService.createOrder(cart,userId),生成订单
        System.out.println("订单号是:"+orderService.createOrder(cart,1));
        System.out.println("订单号是:"+orderService.createOrder(cart,2));
    }

    @Test
    public void showMyOrders() {
        System.out.println(orderService.showMyOrders(1));
    }

    @Test
    public void showAllOrders() {
        System.out.println(orderService.showAllOrders());
    }

    @Test
    public void showOrderDetail() {
        System.out.println(orderService.showOrderDetail("15973143134731"));
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("15973143134731");
    }

    @Test
    public void receiverOrder() {
        orderService.receivedOrder("15973143134731");
    }
}