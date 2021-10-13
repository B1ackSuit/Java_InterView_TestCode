package cn.ean.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName:ReentrantLockObject
 * Author:ean
 * Date:2021/9/29 6:34 下午
 **/
public class ReentrantLockObject {

    private ReentrantLock reentrantLock = new ReentrantLock();

    private ReentrantLock reentrantLock2 = new ReentrantLock();

    public ReentrantLockObject(){
        System.out.println("into RLO");
 //       display();
    }

    public void display(){
        System.out.println("reentrantLock() lock() ---->==");
        reentrantLock.lock();

        try {
            System.out.println("==sleep(50) begin");
            Thread.sleep(50);
            System.out.println("sleep(50) end & display()==");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("==<---- reentrantLock() unlock()");
            reentrantLock.unlock();
        }
    }

    public void display2() {
        reentrantLock.lock();
        System.out.println("reentrantLock2.lock() ---->==");

        reentrantLock2.lock();
        try {
            System.out.println("==display2()==");
        } finally {
            System.out.println("==<---- reentrantLock2() unlock()");
            reentrantLock2.unlock();
            reentrantLock.unlock();
        }
    }

}
