package cn.ean.reentrantlock;

/**
 * FileName:SimpleTestThread2
 * Author:ean
 * Date:2021/10/5 8:57 下午
 **/
public class SimpleTestThread2 {
    public String label;
    private SimpleReentrantLock lock = new SimpleReentrantLock();

    public void setLock(SimpleReentrantLock lock) {
        this.lock = lock;
    }

    public void testAWait(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.testSimpleLock(label);
            }
        }).start();
    }

    public void testBreak() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    lock.testBreakAWait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
