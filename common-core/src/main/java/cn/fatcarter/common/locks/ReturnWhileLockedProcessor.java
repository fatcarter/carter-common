package cn.fatcarter.common.locks;

import java.util.concurrent.locks.Lock;

public abstract class ReturnWhileLockedProcessor<T> {
    private final Object key;

    private final LockRegistry lockRegistry;

    public ReturnWhileLockedProcessor(LockRegistry lockRegistry, Object key) {
        this.key = key;
        this.lockRegistry = lockRegistry;
    }

    public final T doWhileLocked() {
        Lock lock = this.lockRegistry.obtain(this.key);
        try {
            lock.lockInterruptibly();
            try {
                return this.whileLocked();
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalArgumentException("Thread was interrupted while performing task", e);
        }
    }

    protected abstract T whileLocked() ;
}

