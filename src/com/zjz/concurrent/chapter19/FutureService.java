package com.zjz.concurrent.chapter19;

/**
 * 主要用于提交任务
 *
 * @param <IN>
 * @param <OUT>
 */
public interface FutureService<IN, OUT> {
    //不需要返回值，Future.get的方法返回的是null
    Future<?> submit(Runnable runnable);

    //需要返回值，Task接口代替了Runnable接口
    Future<OUT> submit(Task<IN, OUT> task, IN input);

    Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback);

    //静态方法创建一个实现
    static <T, R> FutureService<T, R> newService() {
        return new FutureServiceImpl<>();
    }
}
