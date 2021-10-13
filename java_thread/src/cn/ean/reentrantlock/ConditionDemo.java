package cn.ean.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    private Lock reentrantLock = new ReentrantLock();
    private Condition condition1 = reentrantLock.newCondition();
    private Condition condition2 = reentrantLock.newCondition();
    private Condition condition3 = reentrantLock.newCondition();

    public void testConditionMethod1(){
        reentrantLock.lock();
        try {


            awakenCondition2();
            awakenCondition3();
            Thread.sleep(1000);

            System.out.println("method1 end");




        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void testConditionMethod2() {
        reentrantLock.lock();
        try {

            awakenCondition3();

            System.out.println("method2 end");

            awaitCondition2();

//        } catch (InterruptedException e) {
//            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void testConditionMethod3() {
        reentrantLock.lock();
        try {
       //     Thread.sleep(1000);


            awakenCondition2();
            System.out.println("method3 end");

            awaitCondition3();

//        } catch (InterruptedException e) {
//            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    private void awakenCondition1(){
        System.out.println("=========awaken condition1==========");
        condition1.signal();
    }

    private void awakenCondition2(){
        System.out.println("=========awaken condition2==========");
        condition2.signal();
    }

    private void awakenCondition3(){
        System.out.println("=========awaken condition3==========");
        condition3.signal();
    }

    private void awaitCondition1(){
        System.out.println("----------condition1 await--------");
        try {
            condition1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void awaitCondition2(){
        System.out.println("----------condition2 await--------");
        try {
            condition2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void awaitCondition3(){
        System.out.println("----------condition3 await--------");
        try {
            condition3.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
