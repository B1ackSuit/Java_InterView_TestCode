package cn.ean.medium.string;


import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

/**
 * @FileName MyAtoiTest
 * @Author ean
 * @Date 2023/1/17 18:02
 **/
public class MyAtoiTest {


    @Test
    public void testMain() {
       // String s = "  +023aa";
       // String s = "  +03.99999999999aa";
       // String s = "3.1415";
       // String s = ".1";
       // String s = "+";
        //  String s = "-21474836460";
        //  String s = "-91283472332";
          String s = " 1192820738r2";
       // String s = "  000000000  ";
      //  String s = "  ";
       // String s = "-2147483647";
        System.out.println(myAtoi(s));
    }

    public int myAtoi(String s) {

        int tem = 0;
        s = replaceBlank(s);
        if (s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            if (Character.isDigit(s.charAt(0))) {
                return Integer.parseInt(s);
            } else {
                return 0;
            }
        }
        int judgeResult = judgePositiveAndNegative(s);
        if (judgeResult == 0) {
            tem = 1;
        } else if (judgeResult == -1) {
            s = s.substring(1);
            tem = -1;
        } else if (judgeResult == 1) {
            s = s.substring(1);
            tem = 1;
        } else if (judgeResult == 2) {
            return 0;
        } else if (judgeResult == 3) {
            return Integer.parseInt(String.valueOf(s.charAt(0)));
        }
        s = getDigits(s);
        String parseResultStr = parseDigits(s);
        if ("OutOfRange".equals(parseResultStr)) {
            if (tem == -1) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        } else {
            s = parseResultStr;
        }

        return tem * Integer.parseInt(s);
    }

    private String replaceBlank(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                s = s.substring(i);
                return s;
            }
        }
        return "";
    }

    /**
     * -1，代表负数
     * 0，代表无符号，默认是正数
     * 1，代表正数
     * 2，代表error情况
     * 3，代表小数情况
     * @param s
     * @return
     */
    private int judgePositiveAndNegative(String s) {
        boolean loc0 = Character.isDigit(s.charAt(0));
        if (s.length() == 1) {
            if (!loc0) {
                return 2;
            }
            return 0;
        }
        boolean loc1 = Character.isDigit(s.charAt(1));
        if (loc0 && s.charAt(1) == '.') {
            return 3;
        }
        if (!loc0) {
            if (!loc1) {
                return 2;
            }
            if (s.charAt(0) == '-') {
                return -1;
            } else if (s.charAt(0) == '+') {
                return 1;
            } else {
                return 2;
            }
        }

        return 0;
    }

    private String getDigits(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                s = s.substring(0, i);
                return s;
            }
        }
        return s;
    }

    private String parseDigits(String s) {

        char tem;
        for (int i = 0; i < s.length(); i++) {
           if (s.charAt(i) != '0') {
               s = s.substring(i);
               break;
           }
           if (i == s.length() - 1) {
               return "0";
           }
        }
        if (s.length() > 10) {
            return "OutOfRange";
        } else if (s.length() == 10) {
            String temStr = s.substring(0, 9);
            tem = s.charAt(9);
            if (Integer.parseInt(temStr) > Integer.MAX_VALUE / 10 ||
                    (Integer.parseInt(String.valueOf(tem)) > 7 &&
                            Integer.parseInt(temStr) == Integer.MAX_VALUE / 10)) {
                return "OutOfRange";
            }
        }
        return s;
    }


    public int myAtoiSimple(String str) {
        // 利用trim()去除空格
        str = str.trim();
        // 判断是否是 "" 空字符串
        if (str.length() == 0) {
            return 0;
        }
        // 判断第一位是不是数字，并且是不是正负号
        if (!Character.isDigit(str.charAt(0))
                && str.charAt(0) != '-' && str.charAt(0) != '+') {
            return 0;
        }
        // 存储字符串中的数字元素的值，不断变大
        int ans = 0;
        // neg存储是否是正负号，正为false，符号为true，此处只要不是'-'号都是false，无需再判断有没有符号的情况
        boolean neg = str.charAt(0) == '-';
        // i为下标开始处，如果第一位有符号，则从1开始，如果无符号则是0
        int i = !Character.isDigit(str.charAt(0)) ? 1 : 0;
        // 循环条件：下标i小于字符串长度并且下标位置是数字
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            // tmp存储(临界值+当前位置的元素的值)/10后的值。如果neg是正号，则+1
            int tmp = ((neg ? Integer.MIN_VALUE : Integer.MIN_VALUE + 1) + (str.charAt(i) - '0')) / 10;

            if (tmp > ans) {
                return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            // 将字符串中的元素推入ans中，
            ans = ans * 10 - (str.charAt(i++) - '0');
        }
        return neg ? ans : -ans;
    }
}
