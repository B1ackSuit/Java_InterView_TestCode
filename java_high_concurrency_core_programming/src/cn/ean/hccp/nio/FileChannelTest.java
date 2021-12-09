package cn.ean.hccp.nio;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ean
 * @FileName FileChannelTest
 * @Date 2021/12/6 16:17
 **/
public class FileChannelTest {
    @Test
    public void testFileChannel() throws IOException {
        // 1.创建一个RandomAccessFile(随机访问文件)对象
        RandomAccessFile raf = new RandomAccessFile("D:\\Temp\\test.txt", "rw");
        // 通过RandomAccessFile对象的getChannel()方法。FileChannel是抽象类
        FileChannel inChannel = raf.getChannel();
        // 2.创建一个读数据缓冲区对象
        ByteBuffer buf = ByteBuffer.allocate(48);
        // 3.从通道中读取数据
        int bytesRead = inChannel.read(buf);
        // 创建一个写数据缓冲区对象
        ByteBuffer buf2 = ByteBuffer.allocate(48);
        // 写入数据
        buf2.put("fileChannel test".getBytes());
        buf2.flip();
        inChannel.write(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            // 清空缓存区
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        raf.close();
    }
}
