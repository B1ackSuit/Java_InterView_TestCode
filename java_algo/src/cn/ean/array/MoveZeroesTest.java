package cn.ean.array;

import org.junit.jupiter.api.Test;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 说明:
 *
 *     必须在原数组上操作，不能拷贝额外的数组。
 *     尽量减少操作次数。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2ba4i/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author ean
 * @FileName MoveZeroesTest
 * @Date 2022/1/21 4:46 PM
 **/
public class MoveZeroesTest {

    @Test
    public void testMain() {
     //   moveZeroes(new int[] {0,0});
        solution1(new int[] {1,2,0,4,5});
    }

    public void solution1(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
    }

    public void moveZeroes(int[] nums) {
        int zeroCount = 0;

        for (int i = 0; i < nums.length - 1;){
            if (nums[i] == 0) {
                zeroCount++;

                if(zeroCount == nums.length) {
                    return;
                }
                if (i + 1 != nums.length) {
                    System.arraycopy(nums, i + 1, nums, i, nums.length - 1 - i);
                }
                i = 0;
                continue;
            }
            i++;

        }
        int [] zeroCopy = new int[zeroCount];
        System.arraycopy(zeroCopy, 0, nums, nums.length - zeroCount, zeroCount);
        StringBuffer buffer = new StringBuffer();
        for (int i : nums) {
            buffer.append(i);
        }
        System.out.println(buffer);

    }
}
