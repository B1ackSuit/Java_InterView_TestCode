package cn.ean.threadpool;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.defaultThreadFactory;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * FileName:MyThreadPoolExecutor
 * Author:ean
 * Date:2021/10/6 11:48 下午
 **/
public class MyThreadPoolExecutor {
    private ExecutorService threadPools;

    private int nThreads;

    private static final long DEFAULT_KEEPALIVE_MILLIS = 10L;

    public void setNThreads(int nThreads) {
        this.nThreads = nThreads;
    }

    public void testThreadPoolExecutor(){
      //  threadPools  = new ThreadPoolExecutor();

    }

    public void testFixedThreadPool(int nThreads){
        threadPools = new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public void testCachedThreadPool(){
        threadPools = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    public void testSingleThreadExecutor(){
        threadPools = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

}
