package com.zjz.concurrent.chapter22;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AutoSaveThread extends Thread {
    private final Document document;

    public AutoSaveThread(Document document) {
        super("DocumentAutoSaveThread");
        this.document = document;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                document.save();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
