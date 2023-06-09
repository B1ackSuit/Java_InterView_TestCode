package cn.ean.string;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @FileName PalindromeTest
 * @Author ean
 * @Date 2023/1/17 17:32
 **/
public class PalindromeTest {

    @Test
    public void testMain() {
        boolean palindrome = isPalindrome("A man, a plan, a canal: Panama");
        System.out.println(palindrome);
    }

    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int end = s.length() - 1;
        for (int start = 0; start < end; start++, end--) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(String s) {
        int right = s.length() - 1;
        for (int left = 0; left < right; left++, right--) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
            }
        }
        return true;
    }

}
