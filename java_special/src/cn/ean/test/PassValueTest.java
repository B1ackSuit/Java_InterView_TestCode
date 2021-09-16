package cn.ean.test;

/**
 * FileName:MainTest
 * Author:ean
 * Date:2021/8/24 4:40 下午
 **/
public class PassValueTest {

    public static void main(String[] args) {
        int a = 10;


        TestPOJO pojo = new TestPOJO();
        pojo.setI(a);
        System.out.println(pojo.getI());

        pojo.setI(1);
        TestPass pass = new TestPass();
        pass.setPojo(pojo);
        System.out.println("pojo中的i: " + pojo.getI());
        System.out.println("pass中的i: " + pass.getPojo().getI());

        pojo.setI(2);
        System.out.println("====修改pojo中i的值====");
        System.out.println("pojo中的i: " + pojo.getI());
        System.out.println("pass中的i: " + pass.getPojo().getI());

        pass.getPojo().setI(3);
        System.out.println("====修改pass中i的值====");
        System.out.println("pojo中的i: " + pojo.getI());
        System.out.println("pass中的i: " + pass.getPojo().getI());

        TestValue value = new TestValue();
        value.setPojo();
        System.out.println("====测试值传递====");
        System.out.println("pojo中的i: " + pojo.getI());
        System.out.println("pass中的i: " + pass.getPojo().getI());
        System.out.println("value中的i: " + value.getPojo().getI());
    }
}
class TestPOJO{
    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setI2(){
        int i = 11;
        this.i = i;
    }

}
class TestPass{
    private TestPOJO pojo;
    public void setPojo(TestPOJO pojo){
        this.pojo = pojo;
    }
    public TestPOJO getPojo(){
        return pojo;
    }
}
class TestValue{
    private TestPOJO pojo;
    public void setPojo(){
        TestPOJO pojo = new TestPOJO();
        pojo.setI(4);
        this.pojo = pojo;
    }
    public TestPOJO getPojo(){
        return pojo;
    }
}

