package cn.fatcarter.common.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class Incrementer {
    public static ConcurrentMap<String, AtomicLong> incrementMap = new ConcurrentHashMap<>(32);

    public static long increment(String key) {
        return getIncrementer(key).incrementAndGet();
    }

    private static AtomicLong getIncrementer(String key) {
        return incrementMap.computeIfAbsent(key, k -> new AtomicLong(0));
    }

}
