package cn.ean.http;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;

/**
 * @author ean
 * @FileName HtmlProcess
 * @Date 2021/12/9 16:42
 **/
public class HtmlProcess {
    /**
     * 读取本地html文件里的html代码
     */
    public String toHtmlString(File file) {
        StringBuffer htmlSb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            while (br.ready()) {
                htmlSb.append(br.readLine());
            }
            br.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String htmlStr = htmlSb.toString();
        return htmlStr;
    }
}
