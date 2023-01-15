package cn.ean.array;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @FileName IntersectTest
 * @Author ean
 * @Date 2023/1/11 17:08
 **/
public class IntersectTest {

    @Test
    public void testMain() {
        int[] ints = intersect2(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
        int[] ints1 = intersect2(new int[]{4}, new int[]{9, 4, 9, 8, 4});
        System.out.println(Arrays.toString(ints1));
    }



    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> resultList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i : nums1) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                int count = map.get(i) + 1;
                map.put(i, count);
            }
        }
        for (int i : nums2) {
            if (map.containsKey(i) && map.get(i) > 0) {
                int count = map.get(i) - 1;
                map.put(i, count);
                resultList.add(i);

            }
        }
        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }


    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int [] tem = new int[Math.min(nums1.length, nums2.length)];
        int index1 = 0, index2 = 0, indexTem = 0;

        while (index1 < nums1.length && index2 < nums2.length ) {
            if (nums1[index1] == nums2[index2]) {
                tem[indexTem] = nums1[index1];
                indexTem++;
                index1++;
                index2++;
            } else if (nums1[index1] < nums2[index2]) {
                index1++;
            } else {
                index2++;
            }
        }
        int [] result = new int[indexTem];
        System.arraycopy(tem, 0, result, 0, indexTem);
        return result;
    }

}
