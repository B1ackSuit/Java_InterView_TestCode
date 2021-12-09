package cn.ean.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author ean
 * @FileName HttpRequest
 * @Date 2021/12/9 10:18
 **/
public class HttpRequest {

    // 请求方式
    private String method;
    // 请求资源
    private String url;
    // 请求参数
    private Map<String, List<String>> parameterMap;

    private InputStream is;

    private final String CRLF = "\n";

    public HttpRequest(InputStream is) {
        try {
            // 初始化
            method = "";
            url = "";
            parameterMap = new HashMap<>();
            this.is = is;
            byte[] arr = new byte[20000];
            int len = this.is.read(arr);

            // 开始解析请求正文
            if (len > 0) {
                // 请求正文
                String requestInfo = new String(arr, 0, len);
                paraseRequest(requestInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 根据请求正文 --> 解析出请求方式，请求资源地址
    private void paraseRequest(String requestInfo) throws UnsupportedEncodingException {
        // 接收请求参数列表
        String param = "";

        // 获取请求行
        String firstline = requestInfo.substring(0, requestInfo.indexOf(CRLF));
        method = firstline.substring(0, firstline.indexOf("/")).trim();
        String url = firstline.substring(firstline.indexOf("/"), firstline.indexOf("HTTP/")).trim();

        // 根据不同请求方式封装请求参数和请求资源
        if (method.equalsIgnoreCase("post")) {
            this.url = url;
            param = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
        } else if ("get".equalsIgnoreCase(method)) {
            // 判断是否有请求参数
            if (url.contains("?")) {
                String[] arr = url.split("\\?");
                this.url = arr[0];
                param = arr[1];
            } else {
                this.url = url;
            }
        }

        // 进一步解析请求参数，并封装到集合中 格式类似：a=1&b=2&c="你好"
        if (param != "") {
            StringTokenizer token = new StringTokenizer(param, "&");
            while (token.hasMoreElements()) {
                String key_value = (String) token.nextElement();
                String[] ks = key_value.split("=");

                // 请求参数的值为空，手动赋值为null
                if (ks.length == 1) {
                    ks = Arrays.copyOf(ks, 2);
                    ks[1] = null;
                }

                // 将请求参数键值对封装进集合
                if (!parameterMap.containsKey(ks[0].trim())) {
                    parameterMap.put(ks[0].trim(), new ArrayList<String>());
                }

                // 为避免中文乱码，把请求参数的值按utf-8解码再放进集合
                parameterMap.get(ks[0].trim()).add(URLDecoder.decode(ks[1].trim(), "UTF-8"));
            }
        }
    }

    /**
     * 根据name获取单个参数的值
     */
    public String getParameter(String name) {
        String[] values = getParameterValues(name);
        if (values == null) {
            return null;
        } else {
            return values[0];
        }
    }

    /**
     * 获取多个
     */
    public String[] getParameterValues (String name) {
        if (parameterMap.get(name) == null) {
            return null;
        } else {
            List<String> values = parameterMap.get(name);
            // 参数是指定转换成数组的类型
            return values.toArray(new String[values.size()]);
        }
    }

    /**
     * 获取请求资源
     */
    public String getUrl() {
        return this.url;
    }
}
