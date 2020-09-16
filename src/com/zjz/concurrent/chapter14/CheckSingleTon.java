package com.zjz.concurrent.chapter14;

import java.net.Socket;
import java.sql.Connection;

public class CheckSingleTon {
    private volatile static CheckSingleTon instance = null;
    Connection connection;
    Socket socket;

    public CheckSingleTon() {
        this.connection=;
        this.socket=new Socket();
    }

    public static CheckSingleTon getInstance() {
        if (null == instance) {
            synchronized (CheckSingleTon.class) {
                if (null == instance) {
                    instance = new CheckSingleTon();
                }
            }
        }
        return instance;
    }
}
