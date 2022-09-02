package cn.ean.array;

import org.junit.jupiter.api.Test;

/**
 * @author ean
 * @FileName Monkey
 * @Date 2022/6/28 11:03
 **/
public class Monkey {

    @Test
    public void test() {
        int result = 0;
        for (int i = 0; i <= 10; i++) {

            result = (result + 1) * 2;

        }

        System.out.println(result);
    }



}
