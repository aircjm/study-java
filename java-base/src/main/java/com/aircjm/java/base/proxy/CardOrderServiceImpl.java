package com.aircjm.java.base.proxy;

/**
 * @author admin
 */
public class CardOrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    public CardOrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }



    @Override
    public void createOrder(Order order) {
        System.out.println("创建 card order");
        orderDao.insert(order);
    }
}
