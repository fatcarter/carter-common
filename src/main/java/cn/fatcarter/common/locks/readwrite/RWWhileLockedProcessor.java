package cn.fatcarter.common.locks.readwrite;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.function.Function;

public abstract class RWWhileLockedProcessor {

    private final Object key;

    private final RWLockRegistry lockRegistry;

    public static void runWithReadLocked(RWLockRegistry registry, Object key, Runnable runnable) {
        RWWhileLockedProcessor processor = new RWWhileLockedProcessor(registry, key) {
            @Override
            protected void whileLocked() throws Exception {
                runnable.run();
            }
        };
        try {
            processor.doReadWhileLocked();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void runWithWhiteLocked(RWLockRegistry registry, Object key, Runnable runnable) {
        RWWhileLockedProcessor processor = new RWWhileLockedProcessor(registry, key) {
            @Override
            protected void whileLocked() throws Exception {
                runnable.run();
            }
        };
        try {
            processor.doWhiteWhileLocked();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public RWWhileLockedProcessor(RWLockRegistry lockRegistry, Object key) {
        this.key = key;
        this.lockRegistry = lockRegistry;
    }


    public final void doReadWhileLocked() throws Exception {
        doWhileLocked(lockRegistry::obtainReadLock);
    }

    public final void doWhiteWhileLocked() throws Exception {
        doWhileLocked(lockRegistry::obtainWriteLock);
    }


    private void doWhileLocked(Function<Object, Lock> lockSupplier) throws Exception {
        Lock lock = lockSupplier.apply(key);
        try {
            lock.lockInterruptibly();
            try {
                this.whileLocked();
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
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
