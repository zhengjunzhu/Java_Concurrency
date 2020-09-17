package com.zjz.concurrent.chapter17;

/**
 * 读锁
 */
public class ReadLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    public ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            //如果此时有线程正在写操作，或者有写的线程正在等待，并且偏向写的标识为true，此时无法获取读锁，只能挂起
            while (readWriteLock.getWaitingWriters() > 0 ||
                    (readWriteLock.isPreferWriter() && readWriteLock.getWaitingWriters() > 0)) {
                readWriteLock.getMUTEX().wait();
            }
            readWriteLock.incrementReadingReaders();
        }

    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()) {
            readWriteLock.decrementReadingReaders();
            readWriteLock.changePrefer(true);
            //通知唤醒与MUTEX关联的monitor waitset中的线程
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
