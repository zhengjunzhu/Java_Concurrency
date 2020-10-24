package com.zjz.concurrent.chapter26;

public class ProductionChannel {
    //传送带上最多可以有多少个待加工的产品
    private final static int MAX_PROD = 100;
    //主要用来存放待加工的产品，也就是传送带。
    private final Production[] productionQueue;
    //队列尾
    private int tail;
    //队列头
    private int head;
    //当前在流水线上有多少个待加工产品
    private int total;
    //在流水线上工作的工人
    private final Worker[] workers;

    //创建时应指定需要多少个流水线工人
    public ProductionChannel(int workerSize) {
        this.workers = new Worker[workerSize];
        this.productionQueue = new Production[MAX_PROD];
        for (int i = 0; i < workerSize; i++) {
            workers[i] = new Worker("Worker_" + i, this);
            workers[i].start();
        }
    }

    /**
     * 接受来自上游的半成品
     *
     * @param production
     */
    public void offerProduction(Production production) {
        synchronized (this) {
            while (total >= productionQueue.length) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            productionQueue[tail] = production;
            tail = (tail + 1) % productionQueue.length;
            total++;
            this.notifyAll();

        }
    }

    /**
     * 工人Worker线程从传送带上获取产品，并进行加工
     *
     * @return
     */
    public Production takeProduction() {
        synchronized (this) {
            while (total < 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Production production = productionQueue[head];
            head = (head + 1) % productionQueue.length;
            total--;
            this.notifyAll();
            return production;
        }
    }
}
