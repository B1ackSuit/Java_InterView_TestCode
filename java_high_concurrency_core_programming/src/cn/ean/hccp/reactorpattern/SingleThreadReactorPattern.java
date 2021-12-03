package cn.ean.hccp.reactorpattern;






import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author ea
 **/
public class SingleThreadReactorPattern {
    Selector selector;
    ServerSocketChannel serverSocketChannel;

    SingleThreadReactorPattern() throws IOException{
        // Reactor初始化
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress address = new InetSocketAddress("127.0.0.1" ,8080);

        serverSocketChannel.socket().bind(address);
     //   Logger.info("服务端已开始监听：" + address);
    }
}
