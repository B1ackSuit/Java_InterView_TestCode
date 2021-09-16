package cn.ean.simple_use_thread;

/**
 * FileName:simple_use_thread
 * Author:ean
 * Date:2021/9/2 2:55 下午
 **/
public class Simple_Use_Thread {

    public static void main(String[] args){
        Dog dog = new Dog();
        //dog.start();    Runnable没有start()方法
        //通过Thread代理实现，底层使用代理模式
        Thread thread = new Thread(dog);
        thread.start();

        Tiger tiger = new Tiger();
        ThreadProxy proxy = new ThreadProxy(tiger);
        proxy.start();


        Thread thread1 = new Thread(() -> {

            for (int i = 0; i <= 10; i++) {
                System.out.println("lambda + hi " + i + " getName:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();


        Cat cat = new Cat();
        cat.start();


    }
}

//模拟一个极简Thread
class ThreadProxy implements Runnable{

    //设置一个Runnable类型的属性
    private Runnable target = null;

    @Override
    public void run() {
        if (target != null){
            target.run();    //动态绑定(运行类型)（此代码中的是tiger里的run方法）
        }
    }

    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    public void start(){
        start0();
    }

    //无法实现native方法，尽量实现
    public void start0(){
        run();
    }
}

class Tiger implements Runnable {

    @Override
    public void run() {
        System.out.println("tiger + hi : " + " getName:" + Thread.currentThread().getName());
    }
}



class Dog implements Runnable{

    int count = 0;

    @Override
    public void run() {
        while (count <= 10){
            System.out.println("dog + hi " + (count++) + " getName:" + Thread.currentThread().getName());
            //休眠1秒
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class Cat extends Thread{

    int i = 0;
    @Override
    public void run() {

        for (int j = 0; j <= 10; j++) {
            System.out.println("cat + hi " + i++ + " getName:" + Thread.currentThread().getName());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}