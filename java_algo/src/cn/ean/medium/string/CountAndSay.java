package cn.ean.medium.string;

import org.junit.jupiter.api.Test;

/**
 * @FileName CountAndSay
 * @Author ean
 * @Date 2023/2/3 11:00
 **/
public class CountAndSay {

    @Test
    public void testCountAndSay() {
        System.out.println(countAndSay(4));
    }

    public String countAndSay(int n) {
        String result = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder tem = new StringBuilder();
            int left = 0;
            int right = 0;
            while (right < result.length()) {
                while (right < result.length() && result.charAt(left) == result.charAt(right)) {
                    right++;
                }
                tem.append(right - left).append(result.charAt(left));
                left = right;
            }
            result = tem.toString();
        }
        return result;
    }


    public String countAndSay2(int n) {
        String str = "1";
        for (int i = 2; i <= n; ++i) {
            StringBuilder sb = new StringBuilder();
            int start = 0;
            int pos = 0;

            while (pos < str.length()) {
                while (pos < str.length() && str.charAt(pos) == str.charAt(start)) {
                    pos++;
                }
                sb.append(pos - start).append(str.charAt(start));
                start = pos;
            }
            str = sb.toString();
        }

        return str;
    }

}
