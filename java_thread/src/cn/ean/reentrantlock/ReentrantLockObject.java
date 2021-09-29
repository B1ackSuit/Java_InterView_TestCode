package cn.ean.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName:ReentrantLockObject
 * Author:ean
 * Date:2021/9/29 6:34 下午
 **/
public class ReentrantLockObject {

    private ReentrantLock reentrantLock = new ReentrantLock();

    public ReentrantLockObject(){
        System.out.println("into RLO");
        display();
    }

    public void display(){
        reentrantLock.lock();

        try {
            System.out.println("RLO display()");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("RLO display() unlock()");
            reentrantLock.unlock();
        }
    }
}
