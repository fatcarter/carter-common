package cn.fatcarter.common.locks;

import java.io.IOException;
import java.util.concurrent.locks.Lock;

public abstract class WhileLockedProcessor {

    private final Object key;

    private final LockRegistry lockRegistry;

    public WhileLockedProcessor(LockRegistry lockRegistry,  Object key) {
        this.key = key;
        this.lockRegistry = lockRegistry;
    }

    public final void doWhileLocked() throws Exception {
        Lock lock = this.lockRegistry.obtain(this.key);
        try {
            lock.lockInterruptibly();
            try {
                this.whileLocked();
            }
            finally {
                lock.unlock();
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalArgumentException("Thread was interrupted while performing task", e);
        }
    }

    /**
     * Override this method to provide the behavior that needs to be executed
     * while under the lock.
     *
     * @throws IOException Any IOException.
     */
    protected abstract void whileLocked() throws Exception;

}

