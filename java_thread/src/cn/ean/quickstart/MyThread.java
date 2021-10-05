package cn.ean.quickstart;

/**
 * FileName:MyThread
 * Author:ean
 * Date:2021/10/4 5:12 下午
 **/
public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("Thread run()");
    }
}
