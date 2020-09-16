package com.zjz.concurrent.chapter13;

/**
 * 13.3.3
 */
public class ThreadCloseale extends Thread {
    private volatile boolean started = true;

    @Override
    public void run() {
        super.run();
        while (started) {
            System.out.println("ThreadCloseale");
        }
    }

    public void shutdown() {
        this.started = false;
    }
}
