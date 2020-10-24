package com.zjz.concurrent.chapter26;

public class Production extends InstructionBook {
    private final int productionID;

    public Production(int productionID) {
        this.productionID = productionID;
    }

    @Override
    void firstProgress() {
        System.out.println("execute the " + productionID + " first progress");
    }

    @Override
    void secondProgress() {
        System.out.println("execute the " + productionID + " second progress");
    }
}
