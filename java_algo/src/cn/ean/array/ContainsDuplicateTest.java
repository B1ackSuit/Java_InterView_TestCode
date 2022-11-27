package cn.ean.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给你一个整数数组 nums 。如果任意一个值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：true
 *
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：false
 *
 * 示例 3：
 *
 * 输入：nums = [1,1,1,3,3,4,3,2,4,2]
 * 输出：true
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x248f5/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author ean
 * @FileName ContainsDuplicateTest
 * @Date 2022/1/21 1:11 PM
 **/
public class ContainsDuplicateTest {
    public void testMain() {}

    public boolean containsDuplicate(int[] nums) {
        Set set = new HashSet();

        for (int i : nums) {
            if (!set.add(i)){
                return true;
            }
        }

        return false;
    }
}
