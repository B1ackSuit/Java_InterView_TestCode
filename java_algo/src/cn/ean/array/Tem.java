package cn.ean.array;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ean
 * @FileName Tem
 * @Date 2022/11/10 10:41
 **/
public class Tem {

    @Test
    public void testTem() {
        int [] nums = new int[]{10, 20, 30, 40, 50, 60};
        int l = 1;
        int r = 3;
        nums[l] = nums[r];
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ", ");
        }
    }






}

