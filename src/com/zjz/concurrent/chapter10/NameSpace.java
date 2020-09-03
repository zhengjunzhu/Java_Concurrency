package com.zjz.concurrent.chapter10;

/**
 * 10.2.4 类加载器命名空间
 */
public class NameSpace {
    //1.1同一个加载器加载到Class文件
//    public static void main(String[] args) throws ClassNotFoundException {
//        ClassLoader classLoader = NameSpace.class.getClassLoader();
//        Class<?> aClass = classLoader.loadClass("com.zjz.concurrent.chapter10.HelloWorld");
//        Class<?> bClass = classLoader.loadClass("com.zjz.concurrent.chapter10.HelloWorld");
//        System.out.println("aClass.hashCode() = " + aClass.hashCode());
//        System.out.println("bClass.hashCode() = " + bClass.hashCode());
//        System.out.println("aClass==bClass : " + (aClass == bClass));
//    }


    /**
     * 1.2不同类加载器加载同一个class
     * @param args
     * @throws ClassNotFoundException
     */
//    public static void main(String[] args) throws ClassNotFoundException {
//        MyClassLoader classLoader = new MyClassLoader(null, "E:\\classloader1");
//        BrokenDelegateClassLoader brokenDelegateClassLoader = new BrokenDelegateClassLoader(null, "E:\\classloader1");
//        Class<?> aClass = classLoader.loadClass("com.zjz.concurrent.chapter10.HelloWorld");
//        Class<?> bClass = brokenDelegateClassLoader.loadClass("com.zjz.concurrent.chapter10.HelloWorld");
//
//        System.out.println("aClass.getClassLoader() = " + aClass.getClassLoader());
//        System.out.println("bClass.getClassLoader() = " + bClass.getClassLoader());
//        System.out.println("aClass.hashCode() = " + aClass.hashCode());
//        System.out.println("bClass.hashCode() = " + bClass.hashCode());
//        System.out.println("aClass==bClass : " + (aClass == bClass));
//    }

    /**
     * 1.3相同类加载器加载同一个Class，不是同一个Class实例
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader classLoader = new MyClassLoader(null, "E:\\classloader1");
        MyClassLoader classLoader2 = new MyClassLoader(null, "E:\\classloader1");
        Class<?> aClass = classLoader.loadClass("com.zjz.concurrent.chapter10.HelloWorld");
        Class<?> bClass = classLoader2.loadClass("com.zjz.concurrent.chapter10.HelloWorld");

        System.out.println("aClass.getClassLoader() = " + aClass.getClassLoader());
        System.out.println("bClass.getClassLoader() = " + bClass.getClassLoader());
        System.out.println("aClass.hashCode() = " + aClass.hashCode());
        System.out.println("bClass.hashCode() = " + bClass.hashCode());
        System.out.println("aClass==bClass : " + (aClass == bClass));
    }
}
