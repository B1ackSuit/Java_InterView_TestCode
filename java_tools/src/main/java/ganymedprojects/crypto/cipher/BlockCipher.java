package ganymedprojects.crypto.cipher;

/**
 * @author ean
 * @FileName BlockCipher
 * @Date 2022/12/2 18:17
 **/
public interface BlockCipher {

    void init(boolean var1, byte[] var2);

    int getBlockSize();

    void transformBlock(byte[] var1, int var2, byte[] var3, int var4);

}
