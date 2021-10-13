package cn.ean.jvm.memory;

/**
 * FileName:JStackDemo
 * Author:ean
 * Date:2021/10/11 3:43 下午
 **/
public class JStackDemo {
    static A a = new A();
    static B b = new B();

    public static void main(String[] args) {
        new Thread(
                () -> {
                    synchronized (a) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (b) {
                            System.out.println("get a and b");
                        }
                    }
                }
        ).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(
                () -> {
                    synchronized (b) {
                        synchronized (a) {
                            System.out.println("get a and b");
                        }
                    }
                }
        ).start();

    }

}

class A{

}
class B{

}
