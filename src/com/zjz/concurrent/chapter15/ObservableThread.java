package com.zjz.concurrent.chapter15;

public class ObservableThread<T> extends Thread implements Observable {
    private TaskLifecycle<T> lifecycle;
    private Task<T> task;
    private Cycle cycle;

    public ObservableThread(TaskLifecycle<T> lifecycle, Task<T> task) {
        if (task == null) {
            throw new IllegalArgumentException("The task is required");
        }
        this.lifecycle = lifecycle;
        this.task = task;
    }

    public ObservableThread(Task<T> tTask) {
        this(new TaskLifecycle.EmptyLifecycle<>(), tTask);
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }

    @Override
    public final void run() {
        super.run();
        this.update(Cycle.STARTED, null, null);
        try {
            this.update(Cycle.RUNNING, null, null);
            T result = this.task.call();
            this.update(Cycle.DONE, result, null);
        } catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (lifecycle == null) {
            return;
        }
        try {
            switch (cycle) {
                case STARTED:
                    this.lifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifecycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifecycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    this.lifecycle.onError(currentThread(), e);
                    break;
            }
        } catch (Exception e1) {
            if (cycle == Cycle.ERROR) {
                throw e1;
            }
        }

    }
}
