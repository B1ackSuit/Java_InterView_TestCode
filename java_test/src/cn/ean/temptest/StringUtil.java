package cn.ean.temptest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author ean
 * @FileName StringUtil
 * @Date 2022/9/29 15:00
 **/
public class StringUtil {
    public StringUtil() {
    }

    public static byte[] stringToByteArray(String input) {
        try {
            return input.getBytes("UTF-8");
        } catch (UnsupportedEncodingException var2) {
            return input.getBytes();
        }
    }

    public static String byteArrayToString(byte[] byteArray) {
        try {
            return new String(byteArray, "UTF8");
        } catch (UnsupportedEncodingException var2) {
            return new String(byteArray);
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static String removeSepcialChar(String str) {
        byte[] bytes = str.getBytes();
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        byte[] arg5 = bytes;
        int arg4 = bytes.length;

        for(int e = 0; e < arg4; ++e) {
            byte bts = arg5[e];
            if (bts != 10 && bts != 13 && bts != 9 && bts != 32) {
                bytestream.write(bts);
            }
        }

        byte[] arg8 = bytestream.toByteArray();

        try {
            bytestream.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        try {
            return new String(arg8, "UTF8");
        } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
            return str;
        }
    }
}