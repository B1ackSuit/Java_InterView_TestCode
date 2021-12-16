package cn.ean.http;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * @author ean
 * @FileName ServletFactory
 * @Date 2021/12/16 16:44
 **/
public class ServletFactory {

    // Servlet上下文环境
    private static ServletContext context = new ServletContext();

    // web.xml文件路径
    private static String xmlPath = "src/web.xml";

    public ServletFactory() {
    }

    static {
        try {
            // 获得document
            SAXReader saxReader = new SAXReader();
            File f = new File(xmlPath);
            System.out.println(f.getAbsolutePath());
            Document document = saxReader.read(new File(xmlPath));

            // 获取根元素
            Element rootElement = document.getRootElement();

            // 获取所有子元素
            List<Element> elements = rootElement.elements();

            // 遍历处理所有子元素
            for (Element e : elements) {
                if ("servlet-mapping".equals(e.getName())) {
                    Element servlet_name = e.element("servlet-name");
                    Element url_pattern = e.element("url-pattern");
                    context.getUrl_map().put(url_pattern.getText(), servlet_name.getText());
                }
                else if ("servlet".equals(e.getName())) {
                    Element servlet_name = e.element("servlet-name");
                    Element servlet_class = e.element("servlet-class");
                    context.getServlet_map().put(servlet_name.getText(),servlet_class.getText());
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    /**
     *  获得Servlet
     */
    public static synchronized Servlet getServlet(String url) throws Exception {
        String servletClass = context.getServlet_map().get(context.getUrl_map().get(url));
        System.out.println(servletClass);
        if (servletClass != null)
            return (Servlet)Class.forName(servletClass).getDeclaredConstructor().newInstance();
        else
            return null;
    }

}
