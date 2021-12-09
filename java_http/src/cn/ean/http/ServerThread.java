package cn.ean.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author ean
 * @FileName ServerThread
 * @Date 2021/12/9 10:06
 **/

public class ServerThread extends Thread{

    private Socket client;

    public ServerThread(Socket client) {
        super();
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println(client.getRemoteSocketAddress());
        BufferedReader is;
        try {
            is = new BufferedReader(new InputStreamReader(client.getInputStream()));
            StringBuffer inStr = new StringBuffer();
            String inHeader = null;
            String line = null;
            line = is.readLine();
            while (line.length() > 0) {
                inStr.append(line + "\n<br>");
                line = is.readLine();
            }
            inHeader = "服务器收到信息：" + inStr.toString();
            System.out.println(inHeader);
            PrintWriter pw = new PrintWriter(client.getOutputStream());

            StringBuffer sb = new StringBuffer();
            sb.append("HTTP/1.1 200 OK \r\n");
            sb.append("Content-Type: text/html \r\n");
            sb.append("\r\n");
            sb.append("<html>\n");
            sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            sb.append("<body>\n");
            sb.append("<h1>" + inHeader + "</h1>\n");
            sb.append("</body>\n");
            sb.append("</html>\n");

            System.out.println(sb.toString());

            //     pw.println("收到客户端数据并返回相同数据：" + line);
            //     String encode = URLEncoder.encode(sb.toString());
            pw.println(sb.toString());
            pw.flush();

            pw.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
