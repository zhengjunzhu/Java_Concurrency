package com.zjz.concurrent.chapter15;

/**
 * 暴露给调用者使用的
 */
public interface Observable {
    //任务生命周期的枚举类型
    enum Cycle {
        STARTED, RUNNING, DONE, ERROR
    }
    //获取当前生命周期的状态
    Cycle getCycle();
    //启动线程，主要是为了屏蔽Thread其他方法
    void start();
    //线程打断，主要是为了屏蔽Thread其他方法
    void interrupt();

}
