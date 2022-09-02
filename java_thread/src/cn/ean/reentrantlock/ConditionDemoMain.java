package cn.ean.reentrantlock;

public class ConditionDemoMain {
    public static void main(String[] args) {
        ConditionDemo demo = new ConditionDemo();
        new Thread(() -> {
            System.out.println("___________thread1________ begin");
            for(int i = 0; i < 5; i++)
                demo.testConditionMethod1();
            System.out.println("___________thread1________ end");
        });

        new Thread(() -> {
            System.out.println("___________thread2________ begin");
            for(int i = 0; i < 5; i++){

                try {

                    Thread.sleep(500);

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

                demo.testConditionMethod2();

                System.out.println("thread2:" + i);

                if (i == 4){

                    demo.testConditionMethod1();

                }
            }

            System.out.println("___________thread2________ end");

        }).start();

        new Thread(() ->{
            
            System.out.println("___________thread3________ begin");

            for(int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                demo.testConditionMethod3();
                System.out.println("thread3:" + i);
                if (i == 4)
                    demo.testConditionMethod1();
            }

            System.out.println("___________thread3________ end");
        }).start();

        System.out.println("main thread end");
    }
}
