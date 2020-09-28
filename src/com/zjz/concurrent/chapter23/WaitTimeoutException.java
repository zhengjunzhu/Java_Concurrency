package com.zjz.concurrent.chapter23;

public class WaitTimeoutException extends Exception {
    public WaitTimeoutException(String message) {
        super(message);
    }
}
