package com.cache.baechu.cache;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class Cache<K, V> {

    protected final Map<K, V> map;
    protected final int capacity;

    protected Cache(Map<K, V> map, int capacity) {
        this.map = map;
        this.capacity = capacity;
    }

    public abstract void put(K key, V value);

    public abstract V get(K key);

    public abstract V remove(K key);

    public abstract void deleteByLRU();

    public abstract void clear();

    public abstract Set<Entry<K, V>> findAll();
}
