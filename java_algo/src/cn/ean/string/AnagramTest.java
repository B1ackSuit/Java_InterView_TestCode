package cn.ean.string;

/**
 * @FileName AnagramTest
 * @Author ean
 * @Date 2023/1/17 17:11
 **/
public class AnagramTest {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int [] tem = new int[26];
        int [] tem2 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            tem[s.charAt(i) - 97]++;
        }
        for (int i = 0; i < t.length(); i++) {
            tem2[t.charAt(i) - 97]++;
        }
        for (int i = 0; i < tem.length; i++) {
            if (tem[i] != tem2[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int [] tem = new int[26];
        for (int i = 0; i < s.length(); i++) {
            tem[s.charAt(i) - 97]++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (--tem[t.charAt(i) - 97] == -1) {
                return false;
            }
        }
        return true;
    }

}
