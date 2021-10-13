package cn.ean.jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName:OOMDemo
 * Author:ean
 * Date:2021/10/11 5:37 下午
 **/
public class OOMDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            String a = "hello";
            while (true) {
                list.add(a);
                a = a + a;
                i++;
            }
        } catch (Throwable r) {
            r.printStackTrace();
            System.out.println(i + "次循环，list空间大小：" + list.size());

        }
    }
}
