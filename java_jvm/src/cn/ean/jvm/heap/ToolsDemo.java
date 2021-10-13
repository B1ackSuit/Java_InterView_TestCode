package cn.ean.jvm.heap;

/**
 * FileName:ToolsDemo
 * Author:ean
 * Date:2021/10/11 10:50 下午
 **/
public class ToolsDemo {
    public static void main(String[] args) {
        try {
            System.out.println("1...");
            Thread.sleep(30000);
            byte[] array = new byte[1024 * 1024 * 10];
            System.out.println("2...");
            Thread.sleep(30000);
            array = null;
            System.gc();
            System.out.println("3...");
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
