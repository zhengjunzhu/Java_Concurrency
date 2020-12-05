package com.zjz.concurrent.chapter27;

import com.zjz.concurrent.chapter19.Future;

public interface OrderService {
    //根据订单编号查询订单明细
    Future<String> findOrderDetails(long orderId);

    /**
     * 提交订单
     *
     * @param account
     * @param orderId
     */
    void order(String account, long orderId);
}
