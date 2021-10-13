package cn.ean.jvm.memory;

import org.junit.jupiter.api.Test;

/**
 * FileName:JVMStacksDemo
 * Author:ean
 * Date:2021/10/10 1:12 上午
 **/
public class JVMStacksDemo {
    public static void main(String[] args) {
        method1();
    }

    private static void method1(){
        method2(1, 2);
    }

    private static int method2(int a, int b){
        int c = a + b;
        return c;
    }

    public void m2(StringBuilder s1){
        s1.append(1);
        s1.append(2);
        s1.append(3);
        System.out.println("m2:" + s1.toString());
    }

    public void m3(StringBuilder s){
        s.append(4);
        s.append(5);
        s.append(6);
        System.out.println("m3:" + s.toString());
    }

    @Test
    public void m1(){
        StringBuilder s = new StringBuilder();

        new Thread(() -> {
            m2(s);
        }).start();

        new Thread(() -> {
            m3(s);
        }).start();

        System.out.println("s: " + s.toString());
    }

}
