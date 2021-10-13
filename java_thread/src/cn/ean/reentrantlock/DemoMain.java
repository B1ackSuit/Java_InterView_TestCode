package cn.ean.reentrantlock;

import org.w3c.dom.ls.LSOutput;

public class DemoMain {
    public static void main(String[] args) {

        ReentrantLockDemo demo = new ReentrantLockDemo();
        ReentrantLockDemo demo2 = new ReentrantLockDemo();

        new Thread(() -> {
            System.out.println("thread1 begin");
            demo.method1();
            System.out.println("thread1 end");
        }).start();



        new Thread(() -> {
            System.out.println("thread2 begin");
            demo2.method2();
            System.out.println("thread2 end");
        }).start();

        System.out.println("main thread end");
    }


}
