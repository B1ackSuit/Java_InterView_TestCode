package cn.ean.mapjdk;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ean
 * @FileName SimulateLinkedHashMap
 * @Date 2022/1/25 5:01 PM
 **/
public class SimulateLinkedHashMap<K,V> extends SimulateHashMap<K,V>
        implements Map<K,V> {

    static class Entry<K,V> extends SimulateHashMap.Node<K,V> {
        Entry<K,V> before, after;
        Entry(int hash, K key, V value, Node<K,V> next) {
            super(hash, key, value, next);
        }
    }
}
