package com.zjz.concurrent.chapter15;

/**
 * 主要用于承载任务的执行单元，代替Runnable
 * @param <T>
 */
public interface Task<T> {
    //执行任务接口，该接口允许有返回值
    T call();
}
