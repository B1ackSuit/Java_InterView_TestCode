package cn.ean.oop;

import java.util.stream.Stream;

/**
 * FileName:JavaObject
 * Author:ean
 * Date:2021/9/4 5:48 下午
 **/
public class JavaObject extends Mid{
    int count = 200;
    public static void main(String[] args) {
        JavaObject object = new JavaObject();
        Mid mid = object;
        Base base = object;
        System.out.println(object.count);
        System.out.println(mid.count);
        System.out.println(base.count);
    }
}

class Base{
    int count = 2;
}
class Mid extends Base{
    int count = 20;
}


