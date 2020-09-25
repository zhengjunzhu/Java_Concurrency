package com.zjz.concurrent.chapter19;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 主要作用于当提交任务时创建一个新的线程来受理该任务，进而达到任务异步执行的效果
 *
 * @param <IN>
 * @param <OUT>
 */
public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {
    //为执行的线程指定名字前缀
    private final static String FUTURE_THREAD_PREFIX = "FUTURE-";
    private final AtomicInteger nextCounter = new AtomicInteger(0);

    private String getNextName() {
        return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        FutureTask<Void> futureTask = new FutureTask<>();
        new Thread(() -> {
            runnable.run();
            futureTask.finish(null);
        }, getNextName()).start();
        return futureTask;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input) {
        FutureTask<OUT> futureTask = new FutureTask<>();
        new Thread(() -> {
            OUT result = task.get(input);
            futureTask.finish(result);
        }, getNextName()).start();
        return futureTask;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback) {
        FutureTask<OUT> futureTask = new FutureTask<>();
        new Thread(() -> {
            OUT result = task.get(input);
            futureTask.finish(result);
            if (null != callback) {
                callback.call(result);
            }
        }, getNextName()).start();
        return futureTask;
    }
}
