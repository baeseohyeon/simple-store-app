package com.cache.baechu;

import com.cache.baechu.scheduler.Scheduler;
import com.cache.baechu.scheduler.Task;
import com.cache.baechu.cache.Cache;
import java.util.Map.Entry;
import java.util.Set;

public class Store<K, V> {

    private final Cache<K, V> store;
    private final Scheduler<K> scheduler;

    public Store(Cache<K, V> store, Scheduler<K> scheduler) {
        this.store = store;
        this.scheduler = scheduler;
    }

    public void put(K key, V value) {
        store.put(key, value);
        scheduler.schedule(key, Task.createTask(store, scheduler, key));
    }

    public V get(K key) {
        return store.get(key);
    }

    public V remove(K key) {
        scheduler.remove(key);
        return store.remove(key);
    }

    public void clear() {
        store.clear();
        scheduler.clear();
    }

    public Set<Entry<K, V>> findAll() {
        return store.findAll();
    }
}
