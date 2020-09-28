package com.zjz.concurrent.chapter23;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Latch latch = new CountDownLatch(4);
        new ProgrammerTravel(latch, "Alax", "Bus").start();
        new ProgrammerTravel(latch, "Jone", "Walking").start();
        new ProgrammerTravel(latch, "Jack", "Subway").start();
        new ProgrammerTravel(latch, "Dillon", "Bicycle").start();
        //当前线程main会进入阻塞，直到四个程序员全部到达目的地。
        //如果有一个没有到达则会无限制的等下去
//        latch.await();
        try {
            latch.await(TimeUnit.SECONDS, 5);
            System.out.println("---- all of programmer arrived ----");
        } catch (WaitTimeoutException e) {
            e.printStackTrace();
        }
    }
}
