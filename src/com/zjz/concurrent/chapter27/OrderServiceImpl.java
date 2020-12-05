package com.zjz.concurrent.chapter27;

import com.zjz.concurrent.chapter19.Future;
import com.zjz.concurrent.chapter19.FutureService;

import java.util.concurrent.TimeUnit;

/**
 * 该类是在执行线程中将要被使用的类
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public Future<String> findOrderDetails(long orderId) {
        return FutureService.<Long, String>newService().submit(input -> {
            try {
                //通过休眠来模拟该方法的耗时操作
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "The order Details Information";
        }, orderId, null);
    }

    @Override
    public void order(String account, long orderId) {
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("process the order for account is " + account + " ,orderId is " + orderId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
