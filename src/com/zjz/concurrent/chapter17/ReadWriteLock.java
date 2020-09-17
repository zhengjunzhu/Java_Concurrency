package com.zjz.concurrent.chapter17;

/**
 * 主要是用于创建 read lock 和 write lock
 */
public interface ReadWriteLock {
    //创建read锁
    Lock readLock();

    //创建write锁
    Lock writeLock();

    //获取当前有多少个线程正在执行写操作
    int getWritingWriters();

    //获取当前有多少个线程 正在等待获取写入锁
    int getWaitingWriters();

    //获取当前有多少线程正在执行读操作
    int getReadingReaders();

    static ReadWriteLock readWriteLock(){
        return new ReadWriteLockImpl();
    }

    static ReadWriteLock readWriteLock(boolean preferWriter){
        return new ReadWriteLockImpl(preferWriter);
    }

}
