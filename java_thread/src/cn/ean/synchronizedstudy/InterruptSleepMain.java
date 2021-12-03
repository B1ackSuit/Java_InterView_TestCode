package cn.ean.synchronizedstudy;

public class InterruptSleepMain {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("thread1");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++){

            }
        });
        thread2.start();

        new Thread(() -> {
            thread2.interrupt();
            boolean interrupted = thread2.isInterrupted();
            System.out.println("thread2 isInterrupted : " + interrupted);
        }).start();

        new Thread(() -> {
            boolean interrupted = thread1.isInterrupted();
            System.out.println("thread1 isInterrupted : " + interrupted);
        }).start();
    }
}
