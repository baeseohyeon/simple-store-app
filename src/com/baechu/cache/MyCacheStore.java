package com.baechu.cache;

import com.baechu.cache.DoubleLinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MyCacheStore<K, V> {

    private final int capacity;
    private final Map<K, V> map;
    private final DoubleLinkedList<K> nodes;

    public MyCacheStore() {
        capacity = 5;
        map = new HashMap<>(capacity);
        nodes = new DoubleLinkedList<>();
    }

    public MyCacheStore(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        nodes = new DoubleLinkedList<>();
    }

    public synchronized void put(K key, V value) {
        if (map.containsKey(key)) {
            nodes.moveToTail(key);
            return;
        }
        deleteByLRU();
        nodes.add(key);
        map.put(key, value);
    }

    public synchronized V get(K key) {
        nodes.moveToTail(key);
        return map.get(key);
    }

    public synchronized V remove(K key) {
        nodes.deleteByKey(key);
        return map.remove(key);
    }

    public synchronized void clear() {
        map.clear();
        nodes.clear();
    }

    public void deleteByLRU() {
        if (map.size() >= capacity) {
            map.remove(nodes.deleteByLRU());
        }
    }

    public synchronized Set<Entry<K, V>> findAll() {
        return map.entrySet();
    }
}