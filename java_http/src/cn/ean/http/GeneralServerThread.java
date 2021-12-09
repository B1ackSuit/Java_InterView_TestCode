package cn.ean.http;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

/**
 * @author ean
 * @FileName GeneralServerThread
 * @Date 2021/12/9 14:26
 **/
public class GeneralServerThread extends Thread{
    private HttpRequest request;  //请求
    private HttpResponse response;   //响应
    private Socket client;

    //初始化request，reponse
    public GeneralServerThread(Socket client) {
        try {
            this.client = client;
            request = new HttpRequest(client.getInputStream());
            response = new HttpResponse(client.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println(client.getRemoteSocketAddress()+" 发出请求");

            //浏览器会默认请求网站图标资源，我们这里忽略掉这个请求
            if (request.getUrl().equals("/favicon.ico")) {
                return;
            }

            HtmlProcess h = new HtmlProcess();
            if (request.getUrl().equals("/"))
            {
                String strHtml=h.toHtmlString(new File("src/index.html"));
                response.print(strHtml);
            }
            else
            {
                String str404=h.toHtmlString(new File("src/404.html"));
                response.print(str404);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
