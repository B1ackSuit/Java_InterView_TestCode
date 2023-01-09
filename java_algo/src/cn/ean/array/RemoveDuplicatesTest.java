package cn.ean.array;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 *
 *
 * 说明:
 *
 * 为什么返回数值是整数，但输出的答案是数组呢?
 *
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 *
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 *
 *
 *
 * 提示：
 *
 *     0 <= nums.length <= 3 * 104
 *     -104 <= nums[i] <= 104
 *     nums 已按升序排列
 *
 * 作者：力扣 (LeetCode)
 * 链接：<a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2gy9m/">...</a>
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 *
 * @author ean
 * @FileName RemoveDuplicates
 * @Date 2022/1/20 5:13 PM
 **/

public class RemoveDuplicatesTest {
    @Test
    public void testMain() {
       // int i = removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
        int i2 = solution1(new int[]{1, 1 ,2});
        int [] tem = new int[]{0,0,1,1,1,2,2,3,3,4};
       // int i3 = removeDuplicates20221110(tem);
     //   int i2 = removeDuplicates(new int[]{1, 1 ,2});
        int i3 = removeDuplicates20230109(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
        System.out.println("result: " + i3);
    }

    /**
     * 不符合原地算法：
     */
    public int removeDuplicates(int [] nums){

        IntStream stream = Arrays.stream(nums);
        Stream<Integer> integerStream = stream.boxed();
        Integer [] integers = integerStream.toArray(Integer [] :: new);
        List<Integer> list = new LinkedList<>(List.of(integers));

        for (int i = 0; i < list.toArray().length-1;) {

            if (list.toArray()[i] == list.toArray()[i+1]) {

                Integer remove = list.remove(i);
                i = 0;

            } else {
                i ++;
            }
        }

        nums = list.stream().mapToInt(Integer::valueOf).toArray();

        for(int i = 0; i < nums.length; i++) {
            System.out.println("nums[" + i + "]:" + nums[i]) ;
        }
        return nums.length;
    }

    public int solution1(int [] nums) {
        int org = 0;

        for (int now = 1; now < nums.length; now++) {
            if(nums[org] != nums[now]) {
                nums[++org] = nums[now];
            }
        }

        return nums.length == 0 ? 0 : org+1;
    }

    //双指针解决
    public int solution02(int[] A) {
        //边界条件判断
        if (A == null || A.length == 0) {
            return 0;
        }
        int left = 0;
        for (int right = 1; right < A.length; right++) {

            //如果左指针和右指针指向的值一样，说明有重复的，
            //这个时候，左指针不动，右指针继续往右移。如果他俩
            //指向的值不一样就把右指针指向的值往前挪
            if (A[left] != A[right]) {
                A[++left] = A[right];
            }
        }
        return ++left;
    }

    /**
     * 2022-11-10 思路
     * 一、找出重复的数组的下标，存到新数组中，最后遍历新数据进行更换
     * 1.利用Set特性
     * 2.前后比较
     * 注：下标获取后无法进行后续处理
     * 注：思路补充：找到第一个重复元素的下标，让其等于后面第一个不在重复元素下标list中的值
     * 注：第一个不在重复下标list中的值无法找到
     */
    public int removeDuplicates20221110(int [] nums){
        int result = nums.length;
        List<Integer> list = new ArrayList();
        Set<Integer> set = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            boolean isDuplicate = set.add(nums[i]);
            if (!isDuplicate) {
                list.add(i);
                result -= 1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (list.contains(i)) {
                if (list.contains(i + 1)) {

                }

            }

        }
        //  int [] tem = new int[]{0,0,1,1,1,2,2,3,3,4};
        //  1,3,4,6,8
        for (int i : nums) {
            System.out.println(i);
        }

        return result;
    }

    /**
     * 20230109
     * 思路：双指针
     * 右指针向右移动与左指针比较，不重复：result+1，重复则继续向右移动
     * 不重复后左指针也向右移动一位，左指针向右移动是指让++左指针=右指针
     * 注意：此时左指针可以代替输出结果，但是因为是下标，所以需要+1
     * @param nums
     * @return 长度
     */
    public int removeDuplicates20230109(int [] nums){
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (nums[left] != nums[right]) {
                nums[++left] = nums[right];
            }
        }
        return ++left;
    }
}
