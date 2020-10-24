package com.zjz.concurrent.chapter26;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Test {
    private final static Random random = new Random(System.currentTimeMillis());
    public static void main(String[] args) {
        final  ProductionChannel channel=new ProductionChannel(5);
        AtomicInteger productionNO=new AtomicInteger();
        IntStream.range(1,8).forEach(i->{
            new Thread(()->{
                while (true){
                    channel.offerProduction(new Production(productionNO.getAndIncrement()));
                    try {
                        TimeUnit.SECONDS.sleep(random.nextInt(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });
    }
}
