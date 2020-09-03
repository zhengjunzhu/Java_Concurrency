package com.zjz.concurrent.chapter10;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BrokenDelegateClassLoader extends ClassLoader {
    private final static Path DEFAULT_CLASS_DIR = Paths.get("E:", "classLoader1");
    private final Path classDir;

    public BrokenDelegateClassLoader() {
        this.classDir = DEFAULT_CLASS_DIR;
    }

    public BrokenDelegateClassLoader(String classDir) {
        this.classDir = Paths.get(classDir);
    }

    public BrokenDelegateClassLoader(ClassLoader parent, String classDir) {
        super(parent);
        this.classDir = Paths.get(classDir);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = this.readClassBytes(name);
        if (classBytes.length == 0) {
            throw new ClassNotFoundException("Can not load the class " + name);
        }
        //调用defineClass方法定义class
        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    /**
     * 将class文件读入内存
     *
     */
    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        //将包名分隔符转换为文件路径分隔符
        String classPath = name.replace(".", "/");
        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        if (!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException("The class " + name + " not found.");
        }
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("load the class " + name + " occur error.", e);
        }
    }
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        //类的全路径进行加锁，确保每个类在多线程的情况下只加载一次
        synchronized (getClassLoadingLock(name)) {
            //已加载类中查看该类是否已经被加载，如果已加载直接返回
            Class<?> klass=findLoadedClass(name);
            if (klass == null) {
                if (name.startsWith("java") || name.startsWith("javax")) {
                    //委托给系统类加载器
                    klass = getSystemClassLoader().loadClass(name);
                } else {
                    //尝试用自定义加载器
                    klass = this.findClass(name);
                    if (klass == null) {
                        //自定义加载器失败，委托父类加载器或者系统类加载器
                        if (getParent() != null) {
                            klass = getParent().loadClass(name);
                        } else {
                            klass = getSystemClassLoader().loadClass(name);
                        }
                    }
                }
            }
            //还是不成功，抛异常
            if (null == klass) throw new ClassNotFoundException("The class " + name + " not found");
            if (resolve) {
                resolveClass(klass);
            }
            return klass;
        }
    }
}
