package com.zjz.concurrent.chapter27;

import java.util.LinkedList;

/**
 * 主要用于传送调用线程通过Proxy提交过来的MethodMessage
 */
public class ActiveMessageQueue {
    private final LinkedList<MethodMessage> messages = new LinkedList<>();

    public ActiveMessageQueue() {
        new ActiveDaemonThread(this).start();
    }

    public void offer(MethodMessage methodMessage) {
        synchronized (this) {
            messages.addLast(methodMessage);
            this.notify();
        }
    }

    public MethodMessage take() {
        synchronized (this) {
            while (messages.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return messages.removeFirst();
        }
    }
}
