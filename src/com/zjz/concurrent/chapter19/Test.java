package com.zjz.concurrent.chapter19;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        FutureService<String, Integer> service = FutureService.newService();

        Future<Integer> future=service.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();
        },"Hello", System.out::println);
        System.out.println(future.get());
    }
}
