package cn.ean.http;

import java.util.HashMap;
import java.util.Map;

/**
 *   Servlet的上下文环境
 */
public class ServletContext {

    //Servlet别名和Servlet类名的映射关系
    private  Map<String,String>  servlet_map;

    //url和 Servlet别名的映射关系
    private  Map<String,String> url_map;

    public ServletContext() {
        servlet_map = new HashMap<>();
        url_map = new HashMap<>();
    }

    public Map<String, String> getServlet_map() {
        return servlet_map;
    }


    public Map<String, String> getUrl_map() {
        return url_map;
    }
}
