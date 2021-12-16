package cn.ean.http;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ean
 * @FileName ServicePool
 * @Date 2021/12/10 9:10
 **/
public class ServicePool<Job extends Runnable> {
    // 任务列表（线程池）
    private final LinkedList<Job> jobsList = new LinkedList<>();

    // 工作线程队列
    private final List<MyWorker> workerList = Collections.synchronizedList(new ArrayList<MyWorker>());

    // 默认工作者线程数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    // 工作者编号生成序号
    private AtomicLong threadNum = new AtomicLong();

    // constructor
    public ServicePool() {
        initWorkerThreadByNum(DEFAULT_WORKER_NUMBERS);
    }
    public ServicePool(int workerNum) {
        initWorkerThreadByNum(workerNum);
    }

    public void initWorkerThreadByNum(int workerNum) {
        for (int i = 0; i < workerNum; i++) {
            MyWorker worker = new MyWorker();
            workerList.add(worker);
            // 工作线程开始消费任务
            new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet()).start();
        }
    }

    // 把任务交给线程池，之后工作线程回去消费它
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobsList) {
                jobsList.addLast(job);
                System.out.println("剩余待处理请求个数：" + ServicePool.this.getJobsize());
                jobsList.notify();
            }
        }
    }

    // 通知所有的工作者线程
    public void shutdown() {
        for (MyWorker e : workerList) {
            e.shutdown();
        }
    }

    // 获取剩余任务个数
    public int getJobsize() {
        return jobsList.size();
    }


    /**
     * 工作线程，消费任务
     */
    private class MyWorker implements Runnable {
        // 是否工作
        private volatile boolean isRunning = true;

        @Override
        public void run() {
            while (isRunning) {
                Job job = null;

                // 同步获取任务
                synchronized (jobsList) {
                    // 如果任务列表为空就等待
                    while (jobsList.isEmpty()) {
                        try {
                            jobsList.wait();
                        } catch (InterruptedException e) {
                            // 感知到被中断就退出
                            return;
                        }
                    }
                    // 获取任务
                    job = jobsList.removeFirst();
                }
                // 执行任务
                if (job != null) {
                    System.out.println("正在处理请求");
                    job.run();
                    System.out.println("处理完成，剩余待处理请求个数：" + ServicePool.this.getJobsize());

                }
            }
        }
        // 关闭线程
        public void shutdown() {
            isRunning = false;
        }
    }


}
