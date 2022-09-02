package cn.ean.hccp.utils;



/**
 * @author ean
 * @FileName Logger
 * @Date 2021/12/3 11:27
 **/
public class Logger {
    /**
     * 信息输出
     */
    public static void o(Object s) {
        System.out.println(s);
    }

    /**
     * 带着方法名输出，方法名称放在前面
     */
    public static void fo(Object s) {
         System.out.println(ReflectionUtil.getCallMethod() + ":" + s);
    }

    /**
     * 带类名+方法名输出
     */
}
