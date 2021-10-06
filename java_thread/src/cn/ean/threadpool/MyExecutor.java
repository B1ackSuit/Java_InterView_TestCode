package cn.ean.threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * FileName:MyExecutor
 * Author:ean
 * Date:2021/10/4 7:36 下午
 **/
public class MyExecutor {
    private int taskSize;

    public void setTaskSize(int taskSize) {
        this.taskSize = taskSize;
    }

    public void ExecutorsTest() throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(taskSize);


        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < taskSize; i++){
            Callable callable = new MyCallable();
            Future future = threadPool.submit(callable);
            futureList.add(future);
        }
        threadPool.shutdown();
        for (Future f : futureList){
            System.out.println("result: " + f.get().toString());
        }
    }

    public void ExecuteTest() {
        ExecutorService threadPool = Executors.newFixedThreadPool(taskSize);
        while (true) {
            threadPool.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(Thread.currentThread().getName() + " is running -----");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
        }
    }
}
