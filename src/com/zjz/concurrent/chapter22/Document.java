package com.zjz.concurrent.chapter22;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Document {
    //文档发生改变 该标志设为true
    private boolean changed = false;
    //一次需要保存的内容，可以理解为内容缓存
    private List<String> content = new ArrayList<>();
    private static AutoSaveThread autoSaveThread;
    private final FileWriter writer;

    private Document(String documentPath, String documentName) throws IOException {
        this.writer = new FileWriter(new File(documentPath, documentName), true);
    }

    public static Document create(String documentPath, String documentName) throws IOException {
        Document document = new Document(documentPath, documentName);
        autoSaveThread = new AutoSaveThread(document);
        autoSaveThread.start();
        return document;
    }

    //文档的编辑
    public void edit(String content) {
        synchronized (this) {
            this.content.add(content);
            this.changed = true;
        }
    }

    //文档关闭时先中断自动保存线程，然后关闭writer释放资源
    public void close() throws IOException {
        autoSaveThread.interrupt();
        writer.close();
    }

    //外部显式保存操作
    public void save() throws IOException {
        synchronized (this) {
            if (!changed) {
                return;
            }
            System.out.println(Thread.currentThread() + " execute the save action");
            for (String cacheLine : content) {
                this.writer.write(cacheLine);
                this.writer.write("\r\n");
            }
            this.writer.flush();
            this.changed = false;
            this.content.clear();
        }
    }
}
