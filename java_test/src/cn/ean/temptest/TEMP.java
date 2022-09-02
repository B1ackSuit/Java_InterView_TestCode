package cn.ean.temptest;

import cn.ean.collectionjdk.SimulateArrayList20220114;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author ean
 * @FileName TEMP
 * @Date 2022/1/17 2:24 PM
 **/
public class TEMP {
    private int a;

    @Test
    public void testSimulateArrayList() {
        List<String> list = new SimulateArrayList20220114<>();

        List<String> list1 = new ArrayList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("c");
        list.add("b");

        boolean c = list.contains("c");
        //   System.out.println(c);

    }

    @Test
    public void testClone() {
        String [] copyFrom = {"1", "2", "3", "4", "5", "6", "7"};
        String [] copyTo = new String[4];
        System.arraycopy(copyFrom, 0, copyTo, 0, 4);
        copyFrom[3] = "8";
        copyTo[3] = "9";
        Stream.of(copyFrom).forEach(System.out::print);
        System.out.println();
        Stream.of(copyTo).forEach(System.out::print);
    }

    @Test
    public void testSubList() {
        String a = "a";
        List<String> strings = new ArrayList<>() {{
            add("1");
            add("2");
            add("3");
            add("4");
        }};
        List s = strings.subList(1,3);
        System.out.println(strings);
        System.out.println(s);
        s.set(1, "9");
        System.out.println(strings);
        System.out.println(s);
    }



    @Test
    public void testFinal() {
        A a = new A(1);
        final A i = a;
        i.setI(2);
        System.out.println(a.getI() + "---" + i.getI());

    }


}
class A {
    private int i;

    public A(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}

