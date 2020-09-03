package com.zjz.concurrent.chapter10;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyclassLoaderTest {
    public static void main(String[] args) {
        try {
//           MyClassLoader classLoader = new MyClassLoader("E:\\Java_Space\\Java_Concurrency\\out\\production\\Java_Concurrency");
//           Class<?> aclass = classLoader.loadClass("com.zjz.concurrent.chapter10.HelloWorld");
//           System.out.println(aclass.getClassLoader());
//
//           Object hello = aclass.newInstance();
//           System.out.println(hello);
//           Method welcomeMethod = aclass.getMethod("welcome");
//           String result = (String) welcomeMethod.invoke(hello);
//           System.out.println("result:" + result);


//            ClassLoader extClassLoader = MyclassLoaderTest.class.getClassLoader().getParent();
//            MyClassLoader classLoader1 = new MyClassLoader(extClassLoader, "E:\\classloader1");
//            Class<?> bclass = classLoader1.loadClass("com.zjz.concurrent.chapter10.HelloWorld");
//            System.out.println(bclass);
//            System.out.println(bclass.getClassLoader());

            MyClassLoader classLoader2 = new MyClassLoader(null, "E:\\classloader1");
            Class<?> cclass = classLoader2.loadClass("com.zjz.concurrent.chapter10.HelloWorld");
            System.out.println(cclass);
            System.out.println(cclass.getClassLoader());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
