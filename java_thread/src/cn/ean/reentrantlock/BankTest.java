package cn.ean.reentrantlock;

import java.util.Random;

/**
 * FileName:UnsynchBankTest
 * Author:ean
 * Date:2021/9/29 3:39 下午
 **/
public class BankTest {
    public static final int NACCOUNTS = 10;    // 账户数目
    public static final double INITAL_BALANCE = 1000;    // 初始金额
    public static final double MAX_AMOUNT = 1000;    // 最大转账金额
    public static final int DELAY = 10;    // 延迟

    public static void main(String[] args) {


        // 指定测试的类
        var bank = new ReentrantLockBank(NACCOUNTS, INITAL_BALANCE);


        for (int i = 0; i < NACCOUNTS; i++){
            int fromAccount = i;    // 当前账户的下标赋值给fromAccount
//======================================================================================================================
            Thread thread = new Thread(
                    () -> {
                        try {
                            while (true){
                                int toAccount = (int) (bank.size() * Math.random());    // 接收金钱的账户下标(随机生成)
                                double amount = MAX_AMOUNT * Math.random();    // 随机生成转账金额
                                bank.transfer(fromAccount, toAccount, amount);
                                Thread.sleep((int) (DELAY * Math.random()));    // 随机休眠

                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                        }
                    }
            );
            thread.start();
//======================================================================================================================

//======================================================================================================================
//            Runnable r = () -> {
//                try {
//                    while (true){
//                        int toAccount = (int) (bank.size() * Math.random());    // 接收金钱的账户下标(随机生成)
//                        double amount = MAX_AMOUNT * Math.random();    // 随机生成转账金额
//                        bank.transfer(fromAccount, toAccount, amount);
//                        Thread.sleep((int) (DELAY * Math.random()));    // 随机休眠
//
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                }
//            };
//
//            var t = new Thread(r);
//            t.start();
//======================================================================================================================
        }
    }
}
