package cn.ean.reentrantlock;

/**
 * FileName:SimpleTest
 * Author:ean
 * Date:2021/10/5 8:54 下午
 **/
public class SimpleTestThread1 {
    public static void main(String[] args) {
        SimpleReentrantLock lock = new SimpleReentrantLock();
        SimpleTestThread2 testThread2 = new SimpleTestThread2();
        testThread2.setLock(lock);
        testThread2.label = "await";
        testThread2.testAWait();
        testThread2.testBreak();
        testThread2.label = "no wait";
        testThread2.testAWait();
    }
}
