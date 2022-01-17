package cn.ean.temptest;

import cn.ean.collectionjdk.SimulateArrayList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ean
 * @FileName TEMP
 * @Date 2022/1/17 2:24 PM
 **/
public class TEMP {
    private int a;

    @Test
    public void test() {
        List<String> list;
        list = new SimulateArrayList(1);
        list.add(0, "a");
        list.add(1, "b");
        list.add(2, "c");
        System.out.println(list.size() + " " + ((SimulateArrayList<String>) list).getDataSize());
    }
}
