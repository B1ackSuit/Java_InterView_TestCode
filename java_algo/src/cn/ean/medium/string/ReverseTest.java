package cn.ean.medium.string;

import org.junit.jupiter.api.Test;

/**
 * @FileName ReverseTest
 * @Author ean
 * @Date 2023/1/17 11:27
 **/
public class ReverseTest {

    @Test
    public void testMain() {
        int reverse = reverse(2147483647);
        System.out.println(reverse);
    }

    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }


}
