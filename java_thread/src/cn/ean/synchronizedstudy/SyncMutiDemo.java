package cn.ean.synchronizedstudy;

/**
 * FileName:SyncMutiDemo
 * Author:ean
 * Date:2021/10/17 5:02 下午
 **/
public class SyncMutiDemo {
    public synchronized void method1(int flag){
        System.out.println("into method1");
        if (flag == 0) {
            System.out.println("wait");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if (flag == 1) {
            System.out.println("notify");
            notify();
        }

        System.out.println("success run");

    }
}
