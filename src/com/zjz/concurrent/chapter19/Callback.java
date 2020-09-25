package com.zjz.concurrent.chapter19;

/**
 * 主要用来接受并处理任务的计算结果
 * @param <T>
 */
public interface Callback<T> {
    void call(T t);
}
