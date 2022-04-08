package com.aircjm.study.pattern.proxy;

/**
 * @author admin
 */
public class CommonOrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    public CommonOrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    @Override
    public void createOrder(Order order) {
        System.out.println("创建 common order");
        orderDao.insert(order);
    }
}
