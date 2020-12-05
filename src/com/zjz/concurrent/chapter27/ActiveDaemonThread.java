package com.zjz.concurrent.chapter27;

/**
 * 守护线程 主要是从queue中获取Message然后执行execute方法
 */
public class ActiveDaemonThread extends Thread {
    private final ActiveMessageQueue queue;

    public ActiveDaemonThread(ActiveMessageQueue queue) {
        super("ActiveDaemonThread");
        this.queue = queue;
        setDaemon(true);
    }

    @Override
    public void run() {
        super.run();
        for (; ; ) {
            MethodMessage methodMessage = this.queue.take();
            methodMessage.execute();
        }
    }
}
