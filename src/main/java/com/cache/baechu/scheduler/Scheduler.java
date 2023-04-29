package com.cache.baechu.scheduler;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public record Scheduler<K>(Timer timer, Map<K, Integer> map) {

    private static final int TTL = 3000;

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
