package cn.ean.http;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;

/**
 * @author ean
 * @FileName HttpResponse
 * @Date 2021/12/9 11:02
 **/
public class HttpResponse {
    private StringBuilder headInfo; // 响应行和头信息
    private StringBuilder content; // 正文
    private BufferedWriter bw;
    public int code = 200; // 状态码
    private int len;
    private final String space = " ";
    private final String CRLF = "\n";

    public HttpResponse(OutputStream outputStream) {
        headInfo = new StringBuilder();
        content = new StringBuilder();
        bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        len = 0;
    }

    // 向客户端发送响应
    public void print(String html) throws IOException {
        // 构建响应正文
        content.append(html);
        this.len = html.getBytes().length;
        // 构建响应行和响应头
        setHead();
        // 发送
        bw.append(headInfo.toString());
        bw.append(content.toString());
        bw.flush();
    }

    // 构建响应行和响应头
    private void setHead() {
        if (code != 200) {
            this.len = 0;
        }
        headInfo.append("HTTP/1.1").append(space).append(code).append(space);
        switch (code) {
            case 200:
                headInfo.append("OK");
                break;
            case 404:
                headInfo.append("NOT FOUND");
                break;
            case 505:
                headInfo.append("Server ERROR");
                break;
            default:
                headInfo.append("Something ERROR");
                break;
        }
        headInfo.append(CRLF);
        headInfo.append("Date:").append(LocalDateTime.now()).append(CRLF);
        headInfo.append("Content-Type:text/html;charset=UTF-8").append(CRLF);
        headInfo.append("Content-Length:").append(space).append(this.len).append(CRLF).append(CRLF);
    }
    //
    public void setCode(int code) {
        this.code = code;
    }
}
