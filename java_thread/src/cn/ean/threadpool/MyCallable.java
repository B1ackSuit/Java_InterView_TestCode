package cn.ean.threadpool;

import java.util.concurrent.Callable;

/**
 * FileName:CallableAndFuture
 * Author:ean
 * Date:2021/10/4 5:41 下午
 **/
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "MyCallable call()";
    }
}
