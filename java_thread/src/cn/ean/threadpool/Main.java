package cn.ean.threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * FileName:Main
 * Author:ean
 * Date:2021/10/4 5:43 下午
 **/

public class Main {
    @Test
    public void FutureTaskTest() {
        MyCallable callable = new MyCallable();
        FutureTask<String> stringFutureTask = new FutureTask<>(callable);
        Thread thread = new Thread(stringFutureTask);
        thread.start();
        try {
            String result = stringFutureTask.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ExecutorsTest(){
        MyExecutor executor = new MyExecutor();
        executor.setTaskSize(4);
        try {
            executor.ExecutorsTest();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ExecuteTest(){
        MyExecutor executor = new MyExecutor();
        executor.setTaskSize(4);
        executor.ExecuteTest();
    }
}
