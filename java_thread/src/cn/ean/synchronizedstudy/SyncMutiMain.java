package cn.ean.synchronizedstudy;

/**
 * FileName:SyncMutiMain
 * Author:ean
 * Date:2021/10/17 5:03 下午
 **/
public class SyncMutiMain {

    public static void main(String[] args) {
        SyncMutiDemo demo = new SyncMutiDemo();

        for (int i = 0; i <= 5; i++) {


            new Thread(() -> {

                System.out.println("-----thread1 run method-----");
                demo.method1(0);
                System.out.println("-----thread1 end-----");

            }).start();

            new Thread(() -> {

                System.out.println("-----thread2 run method-----");
                demo.method1(0);
                System.out.println("-----thread2 end-----");

            }).start();


            new Thread(() -> {
                System.out.println("-----thread3 notify-----");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                demo.method1(1);
                System.out.println("-----thread3 end-----");
            }).start();


            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
