package com.zjz.concurrent.chapter10;

public class BrokerDelegateClassLoader extends ClassLoader {

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
