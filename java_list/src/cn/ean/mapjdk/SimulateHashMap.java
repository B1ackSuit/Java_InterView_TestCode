package cn.ean.mapjdk;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.*;


import static java.util.Objects.hash;

/**
 * @author ean
 * @FileName SimulateHashMap
 * @Date 2022/9/2 16:02
 **/
public class SimulateHashMap<K,V> extends AbstractMap<K,V>
        implements Map<K,V>, Cloneable, Serializable {
    Map<K,V> map = new HashMap<>();


    /**
     * 默认第一次添加元素时的初始容量是16
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    /**
     * 最大容量，如果任何一个带参数的构造函数隐式指定了更高的值，则使用该容量代替。必须是2的幂 <= 这个值
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 默认的加载因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 当链表的结点数量大于等于8，并且table数组长度大于等于64时，HashMap会将此链表转换成红黑树
     */
    static final int TREEIFY_THRESHOLD = 8;

    /**
     * 当树的结点被remove到小于等于6时，红黑树会转化成链表
     */
    static final int UNTREEIFY_THRESHOLD = 6;

    /**
     * 树化的第二个必要条件：table数组长度大于等于64
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    /**
     * 填充因子/加载因子 有参构造可以指定，无参构造默认赋值为0.75
     */
    final float loadFactor;

    /**
     * table时HashMap的核心成员变量
     * 该数组用于记录HashMap的所有数据，它的每一个下标都对应一条链表。
     * 即，所有哈希冲突的数据，都会被存放在同一条链表中
     */
    transient Node<K,V>[] table;

    /**
     * 存放元素的个数
     */
    transient int size;

    /**
     * 更改map结构操作时的计数器
     */
    transient int modCount;

    /**
     * 临界值，当实际大小（总容量*填充因子）超过临界值时，数组扩容
     */
    int threshold;

    /**
     * 默认的构造方法，默认的加载因子是0.75，并且在第一次put元素时，从0扩容到16
     * @param loadFactor
     */
    public SimulateHashMap(float loadFactor) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public SimulateHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: "
            + initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: "
            + loadFactor);
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    @Override
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        Node<K,V> [] tab;
        Node<K,V> p;
        int n, i;
        if ((tab = table) == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        if ((p = tab[i = (n - 1) & hash]) == null) {
            tab[i] = newNode(hash, key, value, null);
        }
        return null;
    }

    Node<K, V> newNode(int hash, K key, V value, Node<K,V> next) {
        return new Node<>(hash, key, value, next);
    }

    final Node<K,V>[] resize() {
        Node<K, V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY) {
                newThr = oldThr << 1;
            }
        } else if (oldThr > 0) {
            newCap = oldThr;
        } else {
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float) newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ?
                    (int) ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"rawtypes", "unchecked"})
        Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];
        table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                Node<K, V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null) {
                        newTab[e.hash & (newCap - 1)] = e;
//                    } else if (e instanceof TreeNode) {
//                        ((TreeNode<K, V>) e).split(this, newTab, j, oldCap);
                    } else { // preserve order
                        Node<K, V> loHead = null, loTail = null;
                        Node<K, V> hiHead = null, hiTail = null;
                        Node<K, V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null) {
                                    loHead = e;
                                } else {
                                    loTail.next = e;
                                }
                                loTail = e;
                            } else {
                                if (hiTail == null) {
                                    hiHead = e;
                                } else {
                                    hiTail.next = e;
                                }
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }


    static final int tableSizeFor(int initialCapacity) {
        int n = -1 >>> Integer.numberOfLeadingZeros(initialCapacity - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    @NotNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    static class Node<K,V> implements Map.Entry<K,V> {
        /*
        指key对应的hash值
         */
        final int hash;


        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final K getKey()        { return key; }
        @Override
        public final V getValue()      { return value; }
        @Override
        public final String toString() { return key + "=" + value; }

        /*
        Node对象的hash值
        通过key和value的hash值进行位的异或运算(二进制相同为0，不同为1)
         */
        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }



        /*
        如果传入的元素就是调用的元素，则直接返回true
        否则判断传入的元素是否是Map.Entry的子类或者实现类
        先将强制转换为Map.Entry，然后分别判断key和value是否equals
         */
        @Override
        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue())) {
                    return true;
                }
            }
            return false;
        }
    }


}
