package cn.ean.javalist;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * FileName:HashMapImitate
 * Author:ean
 * Date:2021/9/16 5:39 下午
 **/
public class HashMapImitate<K,V> extends AbstractMap<K,V>
        implements Map<K,V>, Cloneable, Serializable {



    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
