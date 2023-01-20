package com.aircjm.java.base.proxy;

import java.lang.reflect.Proxy;

/**
 * @author admin
 */
public class OrderTest {


    public static void main(String[] args) {

        OrderDao orderDao = new OrderDao();
        OrderService orderService = new CardOrderServiceImpl(orderDao);

        MyTimeProxyInvocationHandler orderProxy = new MyTimeProxyInvocationHandler(orderService);
        OrderService o = (OrderService) Proxy.newProxyInstance(OrderService.class.getClassLoader(), new Class[]{OrderService.class}, orderProxy);

        o.createOrder(new Order());


        OrderService commonOrderService = new CommonOrderServiceImpl(orderDao);
        MyTimeProxyInvocationHandler commonHandler = new MyTimeProxyInvocationHandler(commonOrderService);
        OrderService newProxyInstance = (OrderService) Proxy.newProxyInstance(OrderService.class.getClassLoader(), new Class[]{OrderService.class}, commonHandler);

        newProxyInstance.createOrder(new Order());


    }


}
