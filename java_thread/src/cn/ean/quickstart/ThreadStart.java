package cn.ean.quickstart;

/**
 * FileName:ThreadStart
 * Author:ean
 * Date:2021/10/4 5:11 下午
 **/
public class ThreadStart {
    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRunnable()).start();
    }
}
