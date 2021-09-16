package cn.ean.javalist;

import java.util.EmptyStackException;
import java.util.Vector;

/**
 * FileName:StackImitate
 * Author:ean
 * Date:2021/9/14 5:28 下午
 **/
public class StackImitate<E> extends Vector<E> {
    public StackImitate() {
    }

    public E push(E item) {
        addElement(item);

        return item;
    }

    public synchronized E pop() {
        E       obj;
        int     len = size();

        obj = peek();
        removeElementAt(len - 1);

        return obj;
    }

    public synchronized E peek() {
        int len = size();

        if (len == 0)
            throw new EmptyStackException();
        return elementAt(len - 1);
    }

    public boolean empty() {
        return size() == 0;
    }

    public synchronized int search(Object o) {
        int i = lastIndexOf(o);

        if (i >= 0) {
            return size() - i;
        }
        return -1;
    }
}
