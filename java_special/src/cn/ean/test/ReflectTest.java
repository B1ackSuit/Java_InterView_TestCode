package cn.ean.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * FileName:ReflectTest
 * Author:ean
 * Date:2021/8/28 10:30 下午
 **/
class A{
    public int a = 0;
    private String s = "private s";
    public A(){
        System.out.println("constructor A()");
    }
    public A(String s){
        System.out.println(s);
    }
    private A(String s, Integer a){
        System.out.println(s + a);
    }

    public void display(){
        System.out.println("public void display()");
    }

    public void display(int i){
        System.out.println(i);
    }
    private String display(String s){
        return "private String display()";
    }
}
public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Class clazz = Class.forName("cn.ean.test.A");
        Method method = clazz.getDeclaredMethod("display", String.class);
        System.out.println(method);
    }
}
abstract class TestAb{
    public void test(){
        HashMap map = new HashMap();

    }

}

