package cn.ean.hccp.reactorpattern;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author ean
 * @FileName EchoHandler
 * @Date 2021/12/6 9:25
 **/
class EchoHandler implements Runnable{
    final SocketChannel channel;
    final SelectionKey sk;
    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    //处理器实例的状态：发送和接收，一个连接对应一个处理器实例
    static final int RECIEVING = 0, SENDING = 1;

    int state = RECIEVING;

    // 构造器
    EchoHandler(Selector selector, SocketChannel c) throws IOException {
        channel  = c;
        c.configureBlocking(false);

        // 取得选择键，再设置感兴趣的IO事件
        sk = channel.register(selector, 0);

        // 将Handler自身作为选择键的附件，一个连接对应一个处理器实例
        sk.attach(this);

        // 注册Read就绪事件
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }


    @Override
    public void run() {
        try {
            if (state == SENDING) {
                // 发送状态，把数据写入连接通道
                channel.write(byteBuffer);

                // byteBuffer切换成写模式，写完后，就准备开始从通道读
                byteBuffer.clear();

                // 注册read就绪事件，开始接收客户端数据
                sk.interestOps(SelectionKey.OP_READ);

                // 修改状态，进入接收状态
                state = RECIEVING;
            } else if (state == RECIEVING) {
                // 接收状态，从通道读取数据
                int length = 0;
                while ((length = channel.read(byteBuffer)) > 0) {

                }
                // 读完后，准备开始写入通道,byteBuffer切换成读模式
                byteBuffer.flip();
                // 读完后，注册write就绪事件
                sk.interestOps(SelectionKey.OP_WRITE);
                // 读完后,进入发送的状态
                state = SENDING;
            }
        } catch (IOException e){
            e.printStackTrace();
            sk.cancel();

            try {
                channel.finishConnect();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
