package com.zjz.concurrent.chapter14;

public class HolderSingleTon {
    //HolderSingleTon类里没有静态成员，所以在HolderSingleTon类的初始化中并不会创建HolderSingleTon实例
    private byte[] data = new byte[1024];

    private HolderSingleTon() {
    }

    //Holder类中定义了HolderSingleTon的静态变量并将其实例化，当Holder被主动引用时则会创建HolderSingleTon的实例
    private static class Holder {
        private static HolderSingleTon instance = new HolderSingleTon();
    }

    public static HolderSingleTon getInstance() {
        return Holder.instance;
    }
}
