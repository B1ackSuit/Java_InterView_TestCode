package cn.ean.medium.string;

import org.junit.jupiter.api.Test;

/**
 * @FileName StrStr
 * @Author ean
 * @Date 2023/2/3 00:17
 **/
public class StrStrTest {

    @Test
    public void testMain() {
//        int i = strStr("sadbutsad", "sad");
//        System.out.println(i);
//        String haystack = "sadbutsad";
        String haystack = "abc";
        String needle = "c";
//        String needle = "sad";
        System.out.println(strStr(haystack, needle));
    }

    public int strStr(String haystack, String needle) {
        if (needle.length() >= haystack.length()) {
            if (needle.length() == haystack.length()) {
                if (haystack.equals(needle)) {
                    return 0;
                }
            }
            return -1;
        }
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0) && haystack.length() >= i + needle.length()) {
                String substring = haystack.substring(i, i + needle.length());
                if (substring.equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }

}
