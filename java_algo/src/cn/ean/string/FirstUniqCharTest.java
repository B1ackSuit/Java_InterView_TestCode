package cn.ean.string;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @FileName FirstUniqChar
 * @Author ean
 * @Date 2023/1/17 15:50
 **/
public class FirstUniqCharTest {

    @Test
    public void testMain() {
        System.out.println(firstUniqChar("leletacode"));
    }

    /**
     * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。
     * 如果不存在，则返回 -1 。
     * ascii : 小写97-122
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        Map<Integer, Integer> map = new HashMap<>(16);
        int [] asc = new int[24];
        int tem;
        for (int i = 0; i < chars.length; i++) {
            int asc2code = chars[i];

            asc[asc2code - 97 - 1]++;


            System.out.println("asc2code: " + asc2code + " and chars[i]: " + chars[i]);
            if (map.containsKey(asc2code)) {
                tem = map.get(asc2code) + 1;
                map.put(asc2code, tem);
            } else {
                map.put(asc2code, 0);
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (asc[i] == 0) {
                return i;
            }
        }
        for (int i = 0; i < chars.length; i++) {
            int asc2code = chars[i];
            if (map.get(asc2code) == 0) {
                return i;
            }
        }
        return -1;

    }

    public int firstUniqChar2(String s) {
        Map<Character, Integer> frequency = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); ++i) {
            if (frequency.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

}
