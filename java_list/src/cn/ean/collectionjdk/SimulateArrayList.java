package cn.ean.collectionjdk;

import java.util.*;

/**
 * @author ean
 * @FileName SimulateArrayList
 * @Date 2022/7/12 23:38
 **/
public class SimulateArrayList<E> extends AbstractList<E>
                                  implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    /*
     * ==============================================常量和成员变量
     */

    /**
     * 常量 serialVersionUID
     * 序列化版本UID
     */
    private static final long serialVersionUID = 8695801697902160175L;

    /**
     * 常量 DEFAULT_CAPACITY
     * 默认的数组在第一次存储内容时的扩容大小
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 常量 EMPTY_ELEMENTDATA
     * 空数组，主要用于将elementData赋值为空
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 常量 DEFAULT_CAPACITY_EMPTY_ELEMENTDATA
     * 默认的空数组大小，用于判断以及初始化对象时的赋值
     */
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * 常量 MAX_ARRAY_SIZE
     * 数组长度的上限，Integer的最大值-8，即最大整数值-8
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * transient(不可序列化)数组 elementData
     * 存储元素的数组缓冲区
     */
    transient Object[] elementData;

    /**
     * 私有成员变量 size
     * 用于表示实际有数据的ArrayList数组的大小
     */
    private int size;


    /**
     * 无参构造方法
     *
     * 将elementData赋值为默认的空数组
     *
     * 第一次添加元素的时候会扩充为10
     */
    public SimulateArrayList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 有参构造方法，传入的参数为指定的初始化elementData长度
     *
     * 接收到用户的参数后进行判断：
     * 如果大于0，将elementData设为参数大小的数组
     * 如果等于0，将elementData设为空
     * 其它情况，抛出非法参数异常
     *
     * @param initialCapacity
     */
    public SimulateArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * 有参构造方法，传入的参数为指定集合元素的列表
     *
     * 对传入的集合参数利用Collection的toArray()方法转换为数组
     * 对转换后的数组进行判断：
     * 如果数组大小不为0，将size赋值为数组的大小，然后进行二次判断：
     *    如果传入的参数是ArrayList类，将elementData赋值为转换后的数组
     *    如果是其它情况，利用Arrays.copyOf获得一个 新的 长度与转换后的数组一样的 数组对象，并赋值给elementData
     * 如果数组大小为其它情况，将elementData赋值为空数组
     *
     * @param collection
     */
    public SimulateArrayList(Collection<? extends E> collection) {
        Object[] a = collection.toArray();
        if ((size = a.length) != 0) {
            if (collection.getClass() == ArrayList.class) {
                elementData = a;
            } else {
                elementData = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            // replace with empty array
            elementData = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * 将指定的元素追加到此列表的末尾
     *
     * 计数器加1
     * 调用私有的add(E e, Object[] elementData, int s)方法，后两个参数是elementData和size
     * @param e element whose presence in this collection is to be ensured
     * @return
     */
    @Override
    public boolean add(E e) {
        modCount++;
        add(e, elementData, size);
        return true;
    }

    /**
     * 在此列表中的指定位置插入元素。将当前位于该位置的元素（如果有）和任何后续元素向右移动
     *
     * 调用rangeCheckForAdd(index)方法判断指定的位置是否越界
     * 计数器+1
     * 声明一个不可变变量s，用于保存size的值
     * 声明一个数组变量elementData，用于保存旧的elementData
     * 进行上述两个量的赋值并判断：
     *    当s与elementData的长度一致的时候，即实际使用的长度=数组缓冲区长度，即容量满的时候，需要扩容
     *    扩容即将elementData赋值为grow()方法的返回值
     * 当长度不一致，说明容量没有满，利用System.arraycopy进行深拷贝，把指定位置后面的数组整体后移一位
     * 后移后将指定位置的元素赋值为传入的数据
     * size++
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        modCount++;
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, s - index);
        elementData[index] = element;
        size = s + 1;
    }

    /**
     * 只有add(E e)方法会调用此私有方法
     * 如果size等于缓冲区长度，说明空间已满，需要扩容，新缓冲区的大小为grow()方法的返回值
     * 空间未满则直接在size位置，即在末尾处添加新数据(不是在缓冲区最后)
     * size++
     *
     * @param e
     * @param elementData
     * @param s
     */
    private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = e;
        size = s + 1;
    }

    /**
     * 调用有参grow方法，参数为size + 1
     *
     * @return
     */
    private Object[] grow() {
        return grow(size + 1);
    }

    /**
     * 利用Arrays.copyOf获得一个 新的 长度为newCapacity返回值的 数组对象
     * 参数为minCapacity
     * 如果是add方法调用无参grow()，则无参grow传入的参数为size+1，即最低扩容长度
     * size + 1是因为数组目前已经满了，并且此时又add了一个新的数据，所以扩容后的容量的最低标准是size + 1，所以取名：minCapacity
     *
     * @param minCapacity
     * @return
     */
    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, newCapacity(minCapacity));
    }

    /**
     * oldCapacity为数组之前的长度
     * newCapacity为扩容后的长度(原来的1.5)倍，oldCapacity >> 1即oldCapacity / 2的1次方
     * 如果扩容后的长度 <= 最低长度要求，进行下一步判断
     *    如果数组缓冲区elementData是初始化状态，则取 默认扩容大小10 和 最小扩容长度 的最大值
     *    如果不是初始化状态，判断 最小扩容长度 是否小于0，即如果最小扩容长度<0，说明超出了Integer.MAX_VALUE，true则抛出OOM异常
     * 如果扩容后的长度 > 最低长度要求，进行一个三目运算
     *    如果扩容后的长度 <= 最大数组长度，返回扩容后的长度
     *    否则调用hugeCapacity()方法，将minCapacity作为参数进行下一步判断
     *
     * @param minCapacity
     * @return
     */
    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (elementData == DEFAULT_CAPACITY_EMPTY_ELEMENTDATA) {
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            }
            // overflow
            if (minCapacity < 0) {
                throw new OutOfMemoryError();
            }
            return minCapacity;
        }
        return (newCapacity - MAX_ARRAY_SIZE <= 0) ? newCapacity : hugeCapacity(minCapacity);

    }

    /**
     * 静态私有方法，此时仅适用于部分JVM
     * 如果最小扩容长度<0，说明超出Integer.MAX_VALUE，抛出OOM
     * 如果minCapacity大于Integer.MAX_VALUE-8，此时部分JVM会报错，但还要考虑到部分JVM最大长度超过-8后的值
     *
     * @param minCapacity
     * @return
     */
    private static int hugeCapacity(int minCapacity) {
        // overflow
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * 序列化方法
     *
     */


    /**
     * 在未实现Cloneable接口的实例上调用Object的clone方法会导致抛出异常CloneNotSupportedException
     * 按照惯例，实现此接口的类应使用公共方法覆盖(重载)Object.clone()。
     *
     * 返回此ArrayList实例的浅拷贝对象。
     *
     * @return v
     */
    @Override
    public Object clone() {
        try {
            SimulateArrayList<?> v = (SimulateArrayList<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    /**
     * 判断指定的位置(下标)是否越界
     *
     * @param index
     */
    private void rangeCheckForAdd(int index) {
       if (index > size || index < 0) {
           throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
       }
    }

    /**
     * 越界的报错信息
     *
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }
}
