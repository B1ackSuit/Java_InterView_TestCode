package cn.ean.hccp.utils;

/**
 * @author ean
 * @FileName ReflectionUtil
 * @Date 2021/12/6 9:08
 **/
public class ReflectionUtil {

    /**
     * 获得调用方法的名称
     */
    public static String getCallMethod() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        // 获得调用方法名
        String method = stack[3].getMethodName();
        return method;
    }
}
