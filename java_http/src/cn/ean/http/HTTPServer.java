package cn.ean.http;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;

/**
 * @author ean
 * @FileName HTTPServer
 * @Date 2021/12/9 9:35
 **/
public class HTTPServer {

    @Test
    public void testHttpServer() throws IOException {
        // 创建一个ServerSocket监听8080端口
        ServerSocket server = new ServerSocket(8088);

        // 等待请求
        System.out.println("本机端口：" + String.valueOf(8088) + "服务已经启动，可以与客户端连接");



        while (true) {
            Socket socket = server.accept();
            new GeneralServerThread(socket).start();
        }

    }
}
