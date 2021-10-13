package cn.ean.synchronizedstudy;

public class SyncDemo {

    public synchronized void method1() throws InterruptedException {

        Thread.sleep(2500);
        System.out.println("method1");
    }

    public synchronized void method2(){
        System.out.println("method2");
    }


}
