package cn.ean.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * FileName:TestBlockQueue
 * Author:ean
 * Date:2021/10/7 1:00 上午
 **/
public class TestBlockingQueue {
    private BlockingQueue queue;

    public void testBlockingQueue(){
        queue = new ArrayBlockingQueue();
    }
}
