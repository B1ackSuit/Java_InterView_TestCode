package cn.ean.hccp.reactorpattern;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author ean
 * @FileName ConnectionPerThread
 * @Date 2021/12/3 11:00
 **/

/**
 * 引入原始网络服务器处理传输的方式：
 * while(true) {
 *     socket = accept();    // 阻塞，接收连接
 *     handle(socket);    // 读取数据、业务处理、写入结果
 * }
 * 如果前一个网络连接的handle(socket)没有处理完，后面的请求就会被阻塞，导致服务器的吞吐量太低。
 */

/**
 * 对于每一个新的网络连接都分配给一个线程。
 * 每个线程都独自处理socket连接的输入和输出。
 * 服务器的监听线程也是独立的，任何socket连接的输入和输出处理都不会阻塞到后面新socket连接的监听和建立
 * 早期的tomcat服务器就是这样实现的。
 *
 * 缺点是对于大量的连接，需要耗费大量的线程资源。
 */
class ConnectionPerThread implements Runnable {

    @Override
    public void run() {
        try {
            // 服务器监听socket
            ServerSocket serverSocket = new ServerSocket(8080);
            while (!Thread.interrupted()) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                new Thread(handler).start();
            }
        } catch (IOException e) {

        }
    }

    static class Handler implements Runnable {
        final Socket socket;
        Handler (Socket s) {
            socket = s;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    byte [] input = new byte[1024];
                    // 读取数据
                    socket.getInputStream().read(input);
                    // 处理业务逻辑，获取处理结果
                    byte [] output = null;
                    // 写入结果
                    socket.getOutputStream().write(output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}