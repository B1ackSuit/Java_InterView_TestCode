package cn.ean.test;

/**
 * FileName:NewInstanceTest
 * Author:ean
 * Date:2021/8/29 2:39 下午
 **/
public class NewInstanceTest {
    public static void main(String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        NewInstanceTestClassA a = new NewInstanceTestClassB();
        a.display();

        System.out.println("======new instance=====");
        Class clazz = Class.forName("cn.ean.test.NewInstanceTestClassB");
        Object o = clazz.newInstance();
        System.out.println("======new instance=====");
        NewInstanceTestClassA classA = NewInstanceTestClassB.class.newInstance();
    }
}
interface NewInstanceTestClassA{

    void display();
}
class NewInstanceTestClassB implements NewInstanceTestClassA{

    NewInstanceTestClassB(){
        System.out.println("constructor B");
    }

    @Override
    public void display() {
        System.out.println("Impl A");
    }
}

