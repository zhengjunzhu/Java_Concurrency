package com.zjz.concurrent.chapter17;

/**
 * 读锁
 */
public class WriteLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    public WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            try {
                readWriteLock.incrementWaitingWriters();
                //如果此时有线程正在读操作，或者写操作，挂起当前线程
                while (readWriteLock.getReadingReaders() > 0 ||
                        readWriteLock.getWritingWriters() > 0) {
                    readWriteLock.getMUTEX().wait();
                }
            } finally {
                readWriteLock.decrementWaitingWriters();
            }
            readWriteLock.incrementWritingWriters();
        }

    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()) {
            readWriteLock.decrementWritingWriters();
            readWriteLock.changePrefer(false);
            //通知唤醒与MUTEX关联的monitor waitset中的线程
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
