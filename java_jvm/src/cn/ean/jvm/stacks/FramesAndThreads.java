package cn.ean.jvm.stacks;

/**
 * 黑马 JVM 学习
 * @author ean
 * @FileName FramesAndThreads
 * @Date 2022/6/4 16:18
 **/
public class FramesAndThreads {

    public static void main(String[] args) {
        method1();
    }

    private static void method1() {
        method2(1, 2);
    }

    private static int method2(int a, int b) {
        int c = a + b;
        return c;
    }

}
