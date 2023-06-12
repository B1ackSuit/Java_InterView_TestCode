package cn.ean.string;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @FileName LongestCommonPrefix
 * @Author ean
 * @Date 2023/2/7 16:11
 **/
public class LongestCommonPrefixTest {

    @Test
    public void testLongestCommonPrefix() {
        System.out.println(longestCommonPrefixBinarySearch(
                new String[] {"1234567", "12asgjkaldj", "12asdfaasdf", "12asdfasasdf", "12adafasdfad"}));
    }

    /**
     * 横向扫描
     * 将整个数组的最长公共前缀变成每两个字符串之间的最长公共前缀
     *
     * @param strs
     * @return resultStr
     */
    public String longestCommonPrefixByHorizontalScanning(String[] strs) {
        String resultStr = "";
        int maxLen;
        // 第一个与第二个找出最长前缀n1
        for (int i = 0; i < strs.length; i++) {
            if (i == strs.length - 1) {

            }
            maxLen = Math.max(strs[i].length(), strs[i + 1].length());
            for (int j = 0; j < maxLen; j++) {
                if (strs[i].charAt(j) != strs[i + 1].charAt(j)) {
                    break;
                }
                resultStr += strs[i].charAt(j);
            }

        }

        return resultStr;
    }

    private String longestCommonPrefixByHorizontalScanning2v2(String strs1, String strs2) {
        String resultStr = "";
        int maxLen = Math.max(strs1.length(), strs2.length());
        for (int i = 0; i < maxLen; i++) {
            if (strs1.charAt(i)!= strs2.charAt(i)) {

            }
        }
        return null;
    }





    public String longestCommonPrefix(String[] strs) {
        int shortest = 200;
        int commonCount = 0;
        if (strs.length == 1) {
            return strs[0];
        }
        for (String str : strs) {
            if (str.length() < shortest) {
                shortest = str.length();
            }
        }
        if (shortest == 0) {
            return "";
        }
        for (int i = 0; i < shortest; i++) {
            Set<Character> set = new HashSet<>();
            set.add(strs[0].charAt(i));
            for (int j = 1; j < strs.length; j++) {
                if (set.add(strs[j].charAt(i))) {
                    commonCount = i;
                    return strs[0].substring(0, commonCount);
                }
            }
            commonCount = i;
        }
        return strs[0].substring(0, commonCount + 1);
    }

    public String longestCommonPrefixBinarySearch(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        // 找出最短的元素的长度
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int low = 0;
        int high = minLength;

        // 二分判断
        while (low < high) {
            // 此处，+1是为了让奇数长度的字符，多取一位
            int mid = (high - low + 1) / 2 + low;
            // 判断元素是否都匹配这段字符
            // 如果匹配，则low取中间这段，将后半段进行截取
            // 如果不匹配，则high取前半段的中间部分进行截取
            if (isCommonPrefixBinarySearch(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        return strs[0].substring(0, low);
    }

    /**
     * 将第一个元素从中间截取
     * 第一个for循环是元素的个数
     * 第二个for循环是每个元素的长度
     * 判断每个数组与这段截取的字符进行对比，遇到不一样的返回false
     *
     * @param strs
     * @param length
     * @return
     */
    public boolean isCommonPrefixBinarySearch(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
