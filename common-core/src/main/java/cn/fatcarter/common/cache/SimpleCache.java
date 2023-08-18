package cn.fatcarter.common.cache;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

public class SimpleCache<K, V> implements Iterable<Map.Entry<K, V>>, Serializable {
    private final Map<K, V> cache;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<K, Lock> keyLocks = new ConcurrentHashMap<>();

    public SimpleCache() {
        this(new WeakHashMap<>());
    }

    public SimpleCache(Map<K, V> init) {
        this.cache = init;
    }

    public V get(K k) {
        lock.readLock().lock();
        try {
            return cache.get(k);
        } finally {
            lock.readLock().unlock();
        }
    }

    public V get(K k, Supplier<V> supplier) {
        V v = get(k);
        if (v == null && supplier != null) {
            Lock keyLock = keyLocks.computeIfAbsent(k, kk -> new ReentrantLock());
            keyLock.lock();
            try {
                v = cache.get(k);
                if (v == null) {
                    v = supplier.get();
                    put(k, v);
                }
            } finally {
                keyLock.unlock();
            }
        }
        return v;
    }

    public V put(K k, V v) {
        lock.writeLock().lock();
        try {
            cache.put(k, v);
        } finally {
            lock.writeLock().unlock();
        }
        return v;
    }

    public V remove(K k) {
        lock.writeLock().lock();
        try {
            return cache.remove(k);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clear() {
        lock.writeLock().lock();
        try {
            cache.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }


    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return cache.entrySet().iterator();
    }
}
