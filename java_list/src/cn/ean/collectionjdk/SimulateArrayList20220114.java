package cn.ean.collectionjdk;



import jdk.internal.misc.SharedSecrets;

import java.util.*;

/**
 * @author ean
 * @FileName SimulateArrayList
 * @Date 2022/1/14 3:31 PM
 **/
public class SimulateArrayList20220114<E> extends AbstractList<E>
                                  implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    /*
     * ==================================================常量和成员变量开始
     */

    /**
     * 常量 serialVersionUID
     * 序列化版本UID
     */
    private static final long serialVersionUID = 8683452581122892189L;

    /**
     * 常量 DEFAULT_CAPACITY
     * 默认的数组在第一次存内容时的扩容大小
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 常量 EMPTY_ELEMENTDATA
     * 空数组，主要用于将elementData赋空值
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 常量 DEFAULTCAPACITY_EMPTY_ELEMENTDATA
     * 默认的空数组，主要用于判断和初始化对象时的赋值
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * 常量 MAX_ARRAY_SIZE
     * 数组长度的上限，Integer的最大值-8，即最大整数值-8
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * transient(不可序列化)数组 elementDate
     * 存储元素的数组缓冲区
     */
    transient Object[] elementData;

    /**
     * 私有成员变量 size
     * 用于表示实际有数据的ArrayList数组的大小
     */
    private int size;


    private int index;

    /*
     * 继承自AbstractList，记录ArrayList结构性变化的次数
     *
     * protected transient int modCount;
    */
    /*
     * 常量和成员变量结束==================================================
     */

    /*
     * ==================================================构造方法开始
     */

    /**
     * 无参构造方法
     *
     * 将elementData赋值为默认的空数组
     *
     * 第一次添加元素的时候会扩充为10
     */
    public SimulateArrayList20220114() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }



    /**
     * 有参构造方法，传入的参数为指定的初始化elementData长度
     *
     * 接收到用户的参数后就行判断：
     * 如果大于0，将elementData设为参数大小的数组
     * 如果等于0，将elementData设为空
     * 其它情况，抛非法参数异常
     *
     */
    public SimulateArrayList20220114(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    /**
     * 有参构造方法，传入的参数为指定集合元素的列表
     *
     * 对传入的集合参数利用Collection的toArray()方法转换为数组
     * 对转换后的数组进行判断：
     * 如果数组大小不为0，将size赋值为数组的大小，然后进行二次判断
     *     如果传入的参数是ArrayList类，将elementData赋值为转换后的数组
     *     如果是其它情况，利用Arrays.copyOf获得一个 新的 长度与转换后的数组一样的 数组对象，并赋值给elementData
     * 如果数组大小为其它情况(0)，将elementData赋值为空数组
     *
     */
    public SimulateArrayList20220114(Collection<? extends E> c) {
        Object[] a = c.toArray();
        if ((size = a.length) != 0) {
            if (c.getClass() == ArrayList.class) {
                elementData = a;
            } else {
                elementData = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            // replace with empty array.
            elementData = EMPTY_ELEMENTDATA;
        }
    }

    /*
     * 构造方法结束==================================================
     */

    /*
     * ==================================================add方法&扩容方法开始
     */

    /**
     * 将指定的元素追加到此列表的末尾
     *
     * 计数器加1
     * 调用私有的add(E e, Object [] elementData, int s)方法，后两个参数是elementData和size
     *
     */
    @Override
    public boolean add(E e) {
        modCount++;
        add(e, elementData, size);
        return true;
    }

    /**
     * 在此列表中的指定位置插入指定元素。将当前位于该位置的元素（如果有）和任何后续元素向右移动
     *
     * 调用rangeCheckForAdd(index)方法判断指定的位置是否越界
     * 计数器加1
     * 声明一个不可变属性s，用于保存size的值
     * 声明一个数组变量elementData，用于保存elementData
     * 进行上述两个量的赋值并判断，当s与elementData的长度一致的时候，即实际使用的长度=数组缓冲区长度，即容量满的时候，需要扩容
     *     elementData赋值为grow()方法的返回值
     * 当长度不一致，说明没有满，利用System.arraycopy进行拷贝，把指定位置后面的数组整体后移一位
     * 后移后在指定位置的元素赋值为传入的数据
     * size++
     *
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
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = element;
        size = s + 1;
    }

    /**
     * 只有add(E e)方法会调用此私有方法
     * 如果size等于缓冲区长度，说明空间已满，需要扩容，新缓冲区的大小为grow()的返回值
     * 空间未满则直接在size位置，即在末尾处添加新数据(不是在缓冲区最后)
     * size++
     *
     */
    private void add(E e, Object [] elementData, int s) {
        if (s == elementData.length){
            elementData = grow();
        }
        elementData[s] = e;
        size = s + 1;
    }

    /**
     * 调用有参grow方法，参数为size+1
     *
     */
    private Object[] grow() {
        return grow(size + 1);
    }

    /**
     * 利用Arrays.copyOf获得一个 新的 长度为newCapacity()返回值的 数组对象
     * 参数为minCapacity
     * 如果是add方法调用无参grow()，则无参grow传入的参数为size+1，即最低扩容长度
     * size+1是因为数组目前已经满了，并且此时又add了一个新的数据，所以扩容后的容量的最低标准是size+1，所以取名：minCapacity
     */
    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData,
                newCapacity(minCapacity));
    }

    /**
     * oldCapacity为数组之前的长度
     * newCapacity为扩容后的长度(原1.5倍)，oldCapacity >> 1即oldCapacity / 2的1次方
     * 如果扩容后的长度 <= 最低长度要求，进行下一步判断
     *     如果数组缓冲区elementData是初始化状态，则取 默认扩容大小10 和 最小扩容长度 的最大值
     *     如果不是初始化状态，判断 最小扩容长度 是否小于0，即如果最小扩容长度<0，说明超出了Integer.MAX_VALUE，true则抛OOM异常
     *     既不是初始化状态，也是合法值，返回最小扩容长度
     * 如果扩容后的长度 > 最低长度要求，进行一个三目运算
     *     如果扩容后的长度 <= 最大数组长度，返回扩容后的长度
     *     否则调用hugeCapacity()方法，将minCapacity作为参数进行下一步判断
     *
     */
    private int newCapacity(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        if (newCapacity - minCapacity <= 0) {
            if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            }
            // overflow
            if (minCapacity < 0) {
                throw new OutOfMemoryError();
            }
            return minCapacity;
        }
        return (newCapacity - MAX_ARRAY_SIZE <= 0)
                ? newCapacity
                : hugeCapacity(minCapacity);
    }

    /**
     * 静态私有方法，此时仅实用于部分JVM
     * 如果最小扩容长度<0，说明超出Integer.MAX_VALUE，抛出OOM
     * 如果minCapacity大于Integer.MAX_VALUE-8，此时部分JVM会报错，但还要考虑到部分JVM最大长度超过-8后的值
     *
     */
    private static int hugeCapacity(int minCapacity) {
        // overflow
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }

    /*
     * add方法&扩容方法结束==================================================
     */





    /*
     * ==================================================addAll方法开始
     */


    /**
     * 将指定集合中的所有元素从指定位置开始插入到此列表中。将当前位置的元素(如果有的话)和随后的元素向右移动(增加它们的索引)。
     *
     * 检查指定的位置是否越界
     * 传入的集合通过toArray()方法转换为数组
     * 计数器+1
     * 如果数组长度为0，返回false
     * 如果数组长度 大于 缓冲区长度-已用长度(即剩余可用的长度)
     *     缓冲区长度扩容，调用有参grow，参数为已用长度+数组长度
     * 如果已用长度-要插入的位置 大于 0，即要插入的位置在已用长度中，即需要将要插入位置及其后面的所有已用元素进行移动
     *     通过System.arraycopy()方法，将要插入的位置及其后面的元素进行移动，移动的距离为 已用长度+数组长度
     * 如果已用长度-要插入的位置 不大于 0，则直接通过System.arraycopy()将数组插入到指定位置
     * size为旧的已用长度+数组长度
     *
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        modCount++;
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        }
        Object[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size)) {
            elementData = grow(s + numNew);
        }
        int numMoved = s - index;
        if (numMoved > 0) {
            System.arraycopy(elementData, index,
                    elementData, index + numNew,
                    numMoved);
        }
        System.arraycopy(a, 0, elementData, index, numNew);
        size = s + numNew;
        return true;
    }

    /**
     * 基本逻辑同上，因为没有指定位置，所以直接在末尾加入转换成数组的集合参数
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        modCount++;
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        }
        Object[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size)) {
            elementData = grow(s + numNew);
        }
        System.arraycopy(a, 0, elementData, s, numNew);
        size = s + numNew;
        return true;
    }

    /*
     * addAll方法结束==================================================
     */

    /*
     * ==================================================序列化方法开始
     * 采用这种序列化的原因是：elementData是一个缓存数组，为了性能的考虑，它通常会预留一些容量，当容量不足时会扩充容量，
     * 因此，可能会有大量的空间没有实际存储元素。采用上面的方式来实现序列化可以保证只序列化实际有值的那些元素，而不序列化整个数组。
     */

    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        // Write out element count, and any hidden stuff
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // Write out size as capacity for behavioral compatibility with clone()
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (int i=0; i<size; i++) {
            s.writeObject(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {

        // Read in size, and any hidden stuff
        s.defaultReadObject();

        // Read in capacity
        s.readInt(); // ignored

        if (size > 0) {
            // like clone(), allocate array based upon size not capacity
            SharedSecrets.getJavaObjectInputStreamAccess().checkArray(s, Object[].class, size);
            Object[] elements = new Object[size];

            // Read in all elements in the proper order.
            for (int i = 0; i < size; i++) {
                elements[i] = s.readObject();
            }

            elementData = elements;
        } else if (size == 0) {
            elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new java.io.InvalidObjectException("Invalid size: " + size);
        }
    }

    /*
     * 序列化方法结束==================================================
     */





    /*
     * ==================================================通用方法开始
     */

    /**
     * 在索引为index位置的元素更改为element元素，返回修改前的元素
     *
     * 检查指定的位置是否越界
     * 调用elementData(index)方法，获取指定位置的元素，用于返回
     * 设置缓冲区数组在指定位置的元素为指定的值
     * 返回旧的元素
     */
    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }


    /**
     * 删除并返回指定索引的对象
     *
     * 先检查指定的位置是否越界
     * 将指定位置的元素临时保存，用于返回
     * 调用fastRemove()方法，传入整个缓冲区数组和指定的索引
     */
    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = elementData;

        @SuppressWarnings("unchecked") E oldValue = (E) es[index];
        fastRemove(es, index);

        return oldValue;
    }


    /**
     * 删除指定的元素(如果有：根据下标顺序遍历第一个找到的那个元素)
     *
     * 设置一个标签found
     *     如果指定的元素为null
     *         在已用数组空间内遍历出第一个值为null的元素，跳出标签
     *     否则
     *         在已用数组空间内遍历出第一个值equals指定值的元素，跳出标签
     *     返回失败
     * 调用fastRemove()，参数为缓冲区数组和满足跳出标签的下标
     *
     */
    @Override
    public boolean remove(Object o) {
        final Object[] es = elementData;
        final int size = this.size;
        int i = 0;
        found:
        {
            if (o == null) {
                for (; i < size; i++) {
                    if (es[i] == null) {
                        break found;
                    }
                }
            } else {
                for (; i < size; i++) {
                    if (o.equals(es[i])) {
                        break found;
                    }
                }
            }
            return false;
        }
        fastRemove(es, i);
        return true;
    }

    /**
     * 接收整个缓冲区数组和指定的索引
     * 操作计数器+1
     * 如果size - 1(已用数组末尾的下标) 大于 指定的索引，即要删除的位置有元素
     *     System.arraycopy进行数组拷贝，从指定的索引后面一位(i+1)开始，长度为末尾下标-指定索引，整体前移一位
     * 将缓冲区中，已用数组末尾处清空为null(已经前移)
     */
    private void fastRemove(Object[] es, int i) {
        modCount++;
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }

    /**
     * 获取指定位置的元素，会先检查指定的位置是否越界
     */
    @Override
    public E get(int index) {
        this.index = index;
        Objects.checkIndex(index, size);
        return elementData(index);
    }

    /**
     * 包权限的方法elementData，用于获取指定位置的元素
     */
    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * 判断数组是否为空
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * 获取已用数组空间长度
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 判断指定的位置(下标)是否越界
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * 越界的报错信息
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    private static String outOfBoundsMsg(int fromIndex, int toIndex) {
        return "From Index: " + fromIndex + " > To Index: " + toIndex;
    }



    /*
     * 通用方法结束==================================================
     */



    /**
    临时测试使用
     */
    public int getDataSize(){
        return elementData.length;
    }

    @Override
    public Object clone() {
        try {
            SimulateArrayList20220114<?> v = (SimulateArrayList20220114<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }
}
