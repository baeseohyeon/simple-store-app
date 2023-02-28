package com.cache.baechu.store;

import com.cache.baechu.datastructure.DoubleLinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class CacheStore<K,V> {

    protected final Map<K, V> map;
    protected final DoubleLinkedList<K> nodes;

    protected CacheStore(Map<K, V> map, DoubleLinkedList<K> nodes) {
        this.map = map;
        this.nodes = nodes;
    }

    public abstract void put(K key, V value);

    public abstract V get(K key);

    public abstract V remove(K key);

    public abstract void deleteByLRU();

    public abstract void clear();

    public abstract Set<Entry<K, V>> findAll();
}
