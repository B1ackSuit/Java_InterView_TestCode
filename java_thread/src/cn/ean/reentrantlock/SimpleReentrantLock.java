package cn.ean.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName:SimpleReentrantLock
 * Author:ean
 * Date:2021/10/5 8:48 下午
 **/
public class SimpleReentrantLock {
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final Condition condition;

    public SimpleReentrantLock() {
        condition = reentrantLock.newCondition();
    }

    public void testSimpleLock(String awaitLabel){
        System.out.println("lock");
        reentrantLock.lock();

        try {
            while ("await".equals(awaitLabel)){
                condition.await();
            }
            System.out.println("test simple ReentrantLock ---->>>> " + awaitLabel);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void testBreakAWait() throws InterruptedException {
        System.out.println("lock interruptibility");
        reentrantLock.lockInterruptibly();
        try {
            System.out.println("break a wait");
        } finally {
            reentrantLock.unlock();
            System.out.println("unlock");
        }
    }
}
