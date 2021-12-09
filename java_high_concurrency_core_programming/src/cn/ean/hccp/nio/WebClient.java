package cn.ean.hccp.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author ean
 * @FileName WebClient
 * @Date 2021/12/7 16:41
 **/
public class WebClient {
    @Test
    public void testClient() throws IOException {
        // 1
        SocketChannel socketChannel = SocketChannel.open();

        // 2
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 3333));

        // 3
        ByteBuffer writeBuffer = ByteBuffer.allocate(128);
        writeBuffer.put("hello webserver this is from webclient ".getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);

        //
        ByteBuffer readBuffer = ByteBuffer.allocate(128);
        socketChannel.read(readBuffer);

        StringBuilder stringBuffer = new StringBuilder();

        //
        readBuffer.flip();
        while (readBuffer.hasRemaining()) {
            stringBuffer.append((char) readBuffer.get());
        }

        System.out.println("从服务端接收到的数据: ");

        socketChannel.close();

    }
}
