package com.zjz.concurrent.chapter15;

/**
 * 定义了在任务执行的生命周期中会被触发的接口
 * @param <T>
 */
public interface TaskLifecycle<T> {
    //任务启动
    void onStart(Thread thread);

    //任务正在进行
    void onRunning(Thread thread);

    //任务结束，result是任务执行结束后的结果
    void onFinish(Thread thread, T result);

    //任务执行报错
    void onError(Thread thread, Exception e);

    //空实现
    class EmptyLifecycle<T> implements TaskLifecycle<T> {

        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }
}
