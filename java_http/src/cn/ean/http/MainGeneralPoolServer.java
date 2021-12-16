package cn.ean.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ean
 * @FileName BasicServletFrame
 * @Date 2021/12/10 10:11
 **/
public class MainGeneralPoolServer {
    private static ServicePool<GeneralServerThread> servicePool = new ServicePool<>();

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8089);
            System.out.println("http服务器启动成功...");

            while (true) {
                Socket client = server.accept();
                servicePool.execute(new GeneralServerThread(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
