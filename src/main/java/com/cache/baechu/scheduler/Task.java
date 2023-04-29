package com.cache.baechu.scheduler;

import com.cache.baechu.cache.Cache;
import java.util.TimerTask;

public class Task {

    public static <K, V> TimerTask createTask(Cache<K, V> store, Scheduler<K> scheduler,
        K key) {
        return new TimerTask() {
            @Override
            public void run() {
                scheduler.updateTTLOf(key);
                if (scheduler.isExpired(key)) {
                    store.remove(key);
                    scheduler.remove(key);
                }
            }
        };
    }
}
