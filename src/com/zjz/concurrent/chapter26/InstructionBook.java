package com.zjz.concurrent.chapter26;

/**
 * 在流水线上需要被加工的产品，create作为一个模板方法，提供了加工产品的说明书
 */
public abstract class InstructionBook {
    abstract void firstProgress();

    abstract void secondProgress();

    public final void create() {
        this.firstProgress();
        this.secondProgress();
    }
}
