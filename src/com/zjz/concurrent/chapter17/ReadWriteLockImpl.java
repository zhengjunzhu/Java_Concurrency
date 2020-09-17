package com.zjz.concurrent.chapter17;

public class ReadWriteLockImpl implements ReadWriteLock {

    private final Object MUTEX = new Object();
    private int writingWriters = 0;
    private int waitingWriters = 0;
    private int readingReaders = 0;
    //read 和 write 的偏好设置
    //如果为false，很多线程都在读数据，写线程很难得到机会去修改。
    private boolean preferWriter;

    public ReadWriteLockImpl() {
        this(true);
    }

    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    @Override
    public int getWritingWriters() {
        return this.writingWriters;
    }

    @Override
    public int getWaitingWriters() {
        return this.waitingWriters;
    }

    @Override
    public int getReadingReaders() {
        return this.readingReaders;
    }

    public boolean isPreferWriter() {
        return preferWriter;
    }

    /**
     * TODO MUTEX的作用：虽然在开发一个读写锁,但是在实现的内部也需要一个锁进行数据同步以及线程之间的通信
     *
     * @return
     */
    public Object getMUTEX() {
        return MUTEX;
    }

    void incrementWritingWriters() {
        this.writingWriters++;
    }

    void incrementWaitingWriters() {
        this.waitingWriters++;
    }

    void incrementReadingReaders() {
        this.readingReaders++;
    }

    void decrementWritingWriters() {
        this.writingWriters--;
    }

    void decrementWaitingWriters() {
        this.waitingWriters--;
    }

    void decrementReadingReaders() {
        this.readingReaders--;
    }

    void changePrefer(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }
}
