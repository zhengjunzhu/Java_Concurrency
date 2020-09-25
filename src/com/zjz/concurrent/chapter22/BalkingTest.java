package com.zjz.concurrent.chapter22;

public class BalkingTest {
    public static void main(String[] args) {
        new DocumentEditThread("E:\\","balking.txt").start();
    }
}
