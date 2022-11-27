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
        int [] nums = new int[]{1,1,2};
        int result = removeDuplicates(nums);
        System.out.println(result);
    }

    /**
     * 利用Set的性质
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int [] nums){
        int result = nums.length;
        Set set = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            for (int num : nums){
                boolean isDuplicate = set.add(num);

                if (!isDuplicate) {
                    if (i+1 == nums.length) {
                        nums[i] = nums[i-1];
                    } else {
                        nums[i] = nums[i+1];
                    }

                    result -= 1;
                }
            }
        }

        return result;
    }

    /**
     * 前后比较
     * @param nums
     * @return
     */
    public int removeDuplicates02(int [] nums){
        int result = nums.length;
        Set set = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            for (int num : nums){
                boolean isDuplicate = set.add(num);

                if (!isDuplicate) {
                    if (i+1 == nums.length) {
                        nums[i] = nums[i-1];
                    } else {
                        nums[i] = nums[i+1];
                    }

                    result -= 1;
                }
            }
        }

        return result;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        return null;
    }



}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
