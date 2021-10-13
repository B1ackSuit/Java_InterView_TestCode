package cn.ean.blockingqueue;

import java.util.concurrent.*;


/**
 * FileName:TestBlockQueue
 * Author:ean
 * Date:2021/10/7 1:00 上午
 **/
public class TestBlockingQueue {
    private BlockingQueue queue;
    private ExecutorService service;

    public void testBlockingQueue(){
        queue = new LinkedBlockingQueue();
        service = Executors.newCachedThreadPool();

    }
}
