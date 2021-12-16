package cn.ean.http;

/**
 * @author ean
 * @FileName Servlet
 * @Date 2021/12/16 17:09
 **/
public abstract class Servlet {
    public void service(HttpRequest request, HttpResponse reponse) throws Exception {
        this.doGet(request,reponse);
        this.doPost(request,reponse);
    }

    public abstract void doGet(HttpRequest request, HttpResponse reponse) throws Exception;
    public abstract void doPost(HttpRequest request, HttpResponse reponse) throws Exception;
}
