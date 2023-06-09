package cn.ean.string;

import org.junit.jupiter.api.Test;

/**
 * @FileName ReverseString
 * @Author ean
 * @Date 2023/1/17 10:49
 **/
public class ReverseStringTest {

    @Test
    public void testMain() {
        reverseString(new char[] { 'h', 'e', 'l', 'l', 'o'});
    }

    public void reverseString(char[] s) {
        char temp;
        int end = s.length - 1;
        for (int start = 0; start < end; start++) {
            temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            end--;
        }
    }

}
