package com.zjz.concurrent.chapter23;

import java.util.concurrent.TimeUnit;

public class CountDownLatch extends Latch {
    public CountDownLatch(int limit) {
        super(limit);
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this) {
            while (limit > 0) {
                this.wait();
            }
        }

    }

    @Override
    public void await(TimeUnit timeUnit, long time) throws InterruptedException, WaitTimeoutException {
        if (time < 0) {
            throw new IllegalArgumentException("The time is invalid");
        }
        long remainingNanos = timeUnit.toNanos(time);
        //等待任务将在endNanos纳秒后超时
        final long endNanos = System.nanoTime() + remainingNanos;
        synchronized (this) {
            while (limit > 0) {
                //如果超时则抛出异常
                if (TimeUnit.NANOSECONDS.toMillis(remainingNanos) <= 0) {
                    throw new WaitTimeoutException("The wait time over specify time.");
                }
                //等待remainingNanos 在等待的过程中可能被中断，需要重新计算remainingNanos
                this.wait(TimeUnit.NANOSECONDS.toMillis(remainingNanos));
                remainingNanos = endNanos - System.nanoTime();
            }
        }
    }

    @Override
    public void countDown() {
        synchronized (this) {
            if (limit <= 0) {
                throw new IllegalStateException("all of task already arrived");
            }
            limit--;
            this.notify();
        }
    }

    @Override
    public int getUnarrived() {
        return limit;
    }
}
