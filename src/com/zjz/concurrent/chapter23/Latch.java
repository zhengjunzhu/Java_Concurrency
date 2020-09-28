package com.zjz.concurrent.chapter23;

import java.util.concurrent.TimeUnit;

public abstract class Latch {
    //用于控制多少个线程完成任务时才能打开阀门
    int limit;

    public Latch(int limit) {
        this.limit = limit;
    }

    //该方法使当前线程一直在等待，直到所有的线程都完成工作，被阻塞的线程是允许被中断的。
    //TODO 无限制等待
    public abstract void await() throws InterruptedException;

    //可超时的等待
    public abstract void await(TimeUnit timeUnit, long time) throws InterruptedException, WaitTimeoutException;

    //当任务线程完成工作后调用该方法使得计数器减一
    public abstract void countDown();

    //获取当前线程还有多少没有完成任务
    public abstract int getUnarrived();
}
