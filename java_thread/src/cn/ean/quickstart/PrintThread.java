package cn.ean.quickstart;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * FileName:PrintThread
 * Author:ean
 * Date:2021/9/28 7:57 下午
 **/
public class PrintThread extends Thread{
    private String message;

    public PrintThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(message);
        for (int i = 0; i < 100; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if ( i % 5 == 0){
                System.out.println();
            }
            System.out.print("[" + i + ":" + message + "]");
        }
    }
}

class PrintRunnable implements Runnable{
    private String message;

    public PrintRunnable(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(message);
        for (int i = 0; i < 100; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if ( i % 5 == 0){
                System.out.println();
            }
            System.out.print("[" + i + ":" + message + "]");
        }
    }
}

class Main{
    public static void main(String[] args) {
        new PrintThread("Nice").start();
        new Thread(new PrintRunnable("Good")).start();

        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadFactory.newThread(new PrintThread("Nice")).start();
        if ( !Thread.currentThread().isInterrupted() ){
            System.out.println("isInterrupted is false");
        }
        Thread.interrupted();

        threadFactory.newThread(new PrintRunnable("Good")).start();


    }
}
