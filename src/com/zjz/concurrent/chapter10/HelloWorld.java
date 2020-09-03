package com.zjz.concurrent.chapter10;

/**
 * 被加载的Class
 */
public class HelloWorld {
    static {
        System.out.println("Hello world class is Initialized");
    }

    public String welcome() {
        return "Hello World";
    }
}
