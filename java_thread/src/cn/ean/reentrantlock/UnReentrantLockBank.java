package cn.ean.reentrantlock;

import java.util.Arrays;

/**
 * FileName:ReentrantLockBank
 * Author:ean
 * Date:2021/9/29 3:00 下午
 **/
public class UnReentrantLockBank {

    // 银行账户数组
    private final double[] accounts;

    /* 构造方法
     * @param n 账户数目
     * @param initialBalance 每个账户的初始数值
     */
    public UnReentrantLockBank(int n, double initialBalance) {
        accounts = new double[n];

        for (int i = 0, len = accounts.length; i < len; i++){
            accounts[i] = initialBalance;
        }

        // Arrays.fill(accounts, initialBalance);
    }

    /* 转账并打印转账信息和所有账户的所有金额只和(判断是否出现金额错误)
     * @param from 转账出去的账户
     * @param to 转账收钱的账户
     * @param amount 转账的金额
     *
     */
    public void transfer(int from, int to, double amount){
        if (accounts[from] < amount) return;
        System.out.println(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf("%10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf("Total Balance: %10.2f%n", getTotalBalance());
    }

    /* 计算所有账户的所有金额总和，用于验证金额是否出现错误
     * return 金额总和
     */
    private double getTotalBalance() {
        double sum = 0;

        for (double a : accounts)
            sum += a;

        return sum;
    }

    /* 计算账户的数目
     * return 账户数目
     */
    public int size(){
        return accounts.length;
    }
}
