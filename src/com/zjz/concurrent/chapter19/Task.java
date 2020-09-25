package com.zjz.concurrent.chapter19;

public interface Task<IN, OUT> {
    OUT get(IN input);
}
