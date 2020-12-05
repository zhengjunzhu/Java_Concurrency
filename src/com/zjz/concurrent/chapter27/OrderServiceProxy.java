package com.zjz.concurrent.chapter27;

import com.zjz.concurrent.chapter19.Future;

/**
 * 该类的作用是将OrderService的每一个方法都封装成MethodMessage，然后提交给ActiveMessage队列
 * 在使用OrderService接口方法的时候，实际上是在调用##OrderServiceProxy#中的方法
 */
public class OrderServiceProxy implements OrderService{
    private final OrderService orderService;

    public OrderServiceProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Future<String> findOrderDetails(long orderId) {
        return null;
    }

    @Override
    public void order(String account, long orderId) {

    }
}
