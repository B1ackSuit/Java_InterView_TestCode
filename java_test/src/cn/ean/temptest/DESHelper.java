package cn.ean.temptest;

/**
 * @author ean
 * @FileName Tem
 * @Date 2022/8/10 19:20
 **/

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class DESHelper {
    public DESHelper() {
    }

    public static String encryptDES(String key, String inputString) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        byte[] cipherText = cipherDES(key, StringUtil.stringToByteArray(inputString), 1);
        return StringUtil.byteArrayToString(Base64.encode(cipherText));
    }

    public static byte[] encryptDES(String key, byte[] inputBytes) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        return Base64.encode(cipherDES(key, inputBytes, 1));
    }

    public static String decryptDES(String key, String inputString) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        byte[] cipherText = cipherDES(key, Base64.decode(StringUtil.stringToByteArray(inputString)), 2);
        return StringUtil.byteArrayToString(cipherText);
    }

    public static byte[] decryptDES(String key, byte[] inputBytes) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        return cipherDES(key, Base64.decode(inputBytes), 2);
    }

    public static String encryptDESEDE(String key, String inputString) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        byte[] cipherText = cipherDESEDE(key, StringUtil.stringToByteArray(inputString), 1);
        return StringUtil.byteArrayToString(Base64.encode(cipherText));
    }

    public static byte[] encryptDESEDE(String key, byte[] inputBytes) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        return Base64.encode(cipherDESEDE(key, inputBytes, 1));
    }

    public static String decryptDESEDE(String key, String inputString) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        byte[] cipherText = cipherDESEDE(key, Base64.decode(StringUtil.stringToByteArray(inputString)), 2);
        return StringUtil.byteArrayToString(cipherText);
    }

    public static byte[] decryptDESEDE(String key, byte[] inputBytes) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        return cipherDESEDE(key, Base64.decode(inputBytes), 2);
    }

    private static byte[] cipherDESEDE(String key, byte[] passedBytes, int cipherMode)   throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {

        DESedeKeySpec e = new DESedeKeySpec(StringUtil.stringToByteArray(key));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            SecretKey secretKey = keyFactory.generateSecret(e);
            cipher.init(cipherMode, secretKey);
            return cipher.doFinal(passedBytes);

    }

    private static byte[] cipherDES(String key, byte[] passedBytes, int cipherMode)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {

            DESedeKeySpec e = new DESedeKeySpec(StringUtil.stringToByteArray(key));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            SecretKey secretKey = keyFactory.generateSecret(e);
            cipher.init(cipherMode, secretKey);
            return cipher.doFinal(passedBytes);

    }
}





