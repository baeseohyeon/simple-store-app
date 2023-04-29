package com.cache.baechu.scheduler;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler<K> {

    private static final int TTL = 3000;

    private final Timer timer;

    private final Map<K, Integer> map;

    public Scheduler(Timer timer, Map<K, Integer> map) {
        this.timer = timer;
        this.map = map;
    }

    public void schedule(K key, TimerTask task) {
        timer.schedule(task, TTL);
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    public boolean isExpired(K key) {
        return map.getOrDefault(key, 0) > 0;
    }

    public void remove(K key) {
        map.remove(key);
    }

    public void clear() {
        map.clear();
    }

    public void updateTTLOf(K key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) - 1);
        }
    }
}
