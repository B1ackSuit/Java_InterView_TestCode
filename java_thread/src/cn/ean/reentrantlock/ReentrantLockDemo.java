package cn.ean.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    private Lock reentrantLock = new ReentrantLock();
    private static Lock reentrantLock2 = new ReentrantLock();

    public void method1(){
        reentrantLock.lock();
        try {
            Thread.sleep(2500);
            System.out.println("method1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void method2(){
        System.out.println("lock?");
        reentrantLock2.lock();
        try {
            System.out.println("method2");
        } finally {
            reentrantLock2.unlock();
        }
    }
}
