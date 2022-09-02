package cn.ean.jvm.methodarea;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * FileName:OOMDemo
 * @Author:ean
 * Date:2021/10/11 11:59 下午
 *
 **/
public class OOMDemoMethodArea extends ClassLoader{
    public static void main(String[] args) {
        int j = 0;
        try {
            OOMDemoMethodArea test = new OOMDemoMethodArea();
            for (int i = 0; i < 10000; i++, j++){
                ClassWriter cw = new ClassWriter(0);
                cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i,
                         null, "java/lang/Object", null);
                byte[] code = cw.toByteArray();
                test.defineClass("Class" + i, code, 0, code.length);
            }
//        } catch (ClassFormatError classFormatError) {
//            classFormatError.printStackTrace();

        }finally {
            System.out.println(j);
        }
    }
}
