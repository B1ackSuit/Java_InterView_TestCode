package cn.ean.synchronizedstudy;

public class DemoMain {
    public static void main(String[] args) throws InterruptedException {
        SyncDemo demo = new SyncDemo();
    //    SyncDemo demo2 = new SyncDemo();
        // lambda & stream
        new Thread(() -> {
            System.out.println("thread1 begin");
            try {
                demo.method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread1 end");
        }).start();



        new Thread(() -> {
            System.out.println("thread2 begin");
            demo.method2();
            System.out.println("thread2 end");
        }).start();

        System.out.println("main thread end");
    }
}
