package com.zjz.concurrent.chapter14;

public class EnumSingleTon {
    private enum EnumHolder {
        INSTANCE;
        private EnumSingleTon instance;

        EnumHolder {
            this.instance = new EnumSingleTon();
        }
        private EnumSingleTon getSingleton(){
            return instance;
        }
    }
    public static EnumSingleTon getInstance(){
        return EnumHolder.INSTANCE.getSingleton();
    }
}
