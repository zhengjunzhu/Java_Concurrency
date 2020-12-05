package com.zjz.concurrent.chapter27;

import java.util.Map;

public abstract class MethodMessage {
    /**
     * 用于收集方法参数，如果有返回Future类型则一并收集
     */
    protected final Map<String, Object> params;
    protected final OrderService orderService;

    public MethodMessage(Map<String, Object> params, OrderService orderService) {
        this.params = params;
        this.orderService = orderService;
    }

    public abstract void execute();
}
