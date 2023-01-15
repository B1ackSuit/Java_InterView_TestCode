package cn.ean.array;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * @author ean
 * @FileName SingleNumber
 * @Date 2022/1/18 4:07 PM
 **/

public class SingleNumberTest {
    
    @Test
    public void testMain() {
       // int i = singleNumber(new int[]{2, 0, 2, 4, 4});
        int i = 0;
        System.out.println("i = 0 " + i);
        i = i ^ 2;
        System.out.println("i ^ 2 " + i);
        i = i ^ 1;
        System.out.println("i ^ 1 " + i);
        i = i ^ 4;
        System.out.println("i ^ 4 " + i);
        i = i ^ 2;
        System.out.println("i ^ 2 " + i);
        i = i ^ 1;
        System.out.println("i ^ 1 " + i);
    }
    
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }


    public int singleNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                set.remove(num);
            }
        }
        return (int) set.toArray()[0];
    }

    public int singleNumber3(int [] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            if(i==nums.length-1){
                return nums[i];
            }
            if(nums[i]==nums[i+1]){
                i++;
            }else{
                return nums[i];
            }
        }
        return -1;
    }


    public int singleNumber20230110(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] != nums[i+1]) {
                if (i == 0) {
                    return nums[i];
                } else if (i+2 <= nums.length -1 ) {
                    if (nums[i+1] != nums[i+2]) {
                        return nums[i+1];
                    }
                } else {
                    return nums[i + 1];
                }
            }
        }
        return -1;
    }









}
