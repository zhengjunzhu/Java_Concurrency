package com.zjz.concurrent.chapter23;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 程序员旅游线程，力争在星期六早上十点到达城市广场，然后一同前往同心湖垂钓烧烤。
 */
public class ProgrammerTravel extends Thread {
    private final Latch latch;
    //程序员
    private final String programmer;
    //交通工具
    private final String transportation;

    public ProgrammerTravel(Latch latch, String programmer, String transportation) {
        this.latch = latch;
        this.programmer = programmer;
        this.transportation = transportation;
    }

    @Override
    public void run() {
        super.run();
        System.out.println(programmer + " start take transportation [" + transportation + "]");
        try {
            //程序员在路上花费的时间，使用随机数模拟
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(programmer + " arrived by " + transportation);
        //完成任务时计数器减一
        latch.countDown();
    }
}
