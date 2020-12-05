package com.zjz.concurrent.chapter27;

import java.util.Map;

/**
 * 主要处理Order方法，执行真正的OrderService中的Order方法
 */
public class OrderMessage extends MethodMessage {

    public OrderMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        String account = (String) params.get("account");
        long orderId = (long) params.get("orderId");
        orderService.order(account, orderId);
    }
}
