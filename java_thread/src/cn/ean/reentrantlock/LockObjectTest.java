package cn.ean.reentrantlock;

/**
 * FileName:LockObjectTest
 * Author:ean
 * Date:2021/10/13 9:49 下午
 **/
public class LockObjectTest {
    public static void main(String[] args) {
        ReentrantLockObject lockObject = new ReentrantLockObject();
        Thread thread1 = new Thread(() -> {
            System.out.println("11111111111111111111thread1 begin11111111111111111111");
            lockObject.display();
            System.out.println("11111111111111111111thread1 end11111111111111111111");
        });
        thread1.start();

        new Thread(() -> {
            System.out.println("22222222222222222222thread2 begin22222222222222222222");
            lockObject.display2();
            System.out.println("22222222222222222222thread2 end22222222222222222222");
        }).start();

        System.out.println("_____________main thread end_____________");
    }
}
