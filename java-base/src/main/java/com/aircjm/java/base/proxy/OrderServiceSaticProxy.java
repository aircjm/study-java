package com.aircjm.java.base.proxy;

public class OrderServiceSaticProxy implements OrderService {


    private OrderService orderService;


    public OrderServiceSaticProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void createOrder(Order order) {

        before();

        orderService.createOrder(order);

        after();

    }


    private void before() {
        System.out.println("before function");
    }

    private void after() {
        System.out.println("after function");
    }

}
