package com.cache.baechu.cache;

import com.cache.baechu.datastructure.DoubleLinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LRUCache<K, V> extends Cache<K, V> {

    private final int capacity;

    public LRUCache() {
        super(new HashMap<>(), new DoubleLinkedList<>());
        capacity = 5;
    }

    public LRUCache(int capacity, Map<K, V> map, DoubleLinkedList<K> nodes) {
        super(map, nodes);
        this.capacity = capacity;
    }

    @Override
    public void put(K key, V value) {
        if (map.containsKey(key)) {
            nodes.moveToTail(key);
            return;
        }
        deleteByLRU();
        nodes.add(key);
        map.put(key, value);
    }

    @Override
    public V get(K key) {
        nodes.moveToTail(key);
        return map.get(key);
    }

    @Override
    public V remove(K key) {
        nodes.deleteByKey(key);
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
        nodes.clear();
    }

    @Override
    public void deleteByLRU() {
        if (map.size() >= capacity) {
            map.remove(nodes.deleteByLRU());
        }
    }

    @Override
    public Set<Entry<K, V>> findAll() {
        return map.entrySet();
    }
}