package com.zjz.concurrent.chapter27;

import com.zjz.concurrent.chapter19.Future;

import java.util.Map;

public class FindOrderDetailMessage extends MethodMessage{
    public FindOrderDetailMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        Future<String> realFuture= orderService.findOrderDetails((Long) params.get("orderId"));

        try {
            String result=realFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
