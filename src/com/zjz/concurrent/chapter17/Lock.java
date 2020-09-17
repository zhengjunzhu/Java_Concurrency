package com.zjz.concurrent.chapter17;

/**
 * 定义了锁的基本操作，加锁和解锁
 */
public interface Lock {
    //当前线程尝试获得锁的拥有权，在此期间可能进入阻塞
    void lock() throws InterruptedException;

    //释放锁，主要目的是为了减少reader或者writer的数量
    void unlock();
}
