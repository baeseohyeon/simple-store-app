package com.cache.baechu.scheduler;

import com.cache.baechu.store.CacheStore;
import java.util.TimerTask;

public class Task {

    public static <K, V> TimerTask createTask(CacheStore<K, V> store, Scheduler<K> scheduler,
        K key) {
        return new TimerTask() {
            @Override
            public void run() {
                scheduler.updateTTL(key);
                if (scheduler.get(key) > 0) {
                    return;
                }
                store.remove(key);
                scheduler.remove(key);
            }
        };
    }
}
