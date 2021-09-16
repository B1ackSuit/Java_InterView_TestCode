package cn.ean.exit;

/**
 * FileName:ThreadExit
 * Author:ean
 * Date:2021/9/2 4:30 下午
 **/
public class ThreadExit {
    public static void main(String[] args) {
        T t1 = new T();
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //希望main控制线程t1，修改控制变量loop即可
        t1.setLoop(false);

    }
}

class T extends Thread{

    private int count = 0;
    //设置一个控制变量
    private boolean loop = true;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    @Override
    public void run() {
        while (loop){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程T运行中 " + ++count);
        }
    }
}