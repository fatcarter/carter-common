package cn.fatcarter.common.locks.readwrite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.function.Function;

public abstract class RWWhileLockedProcessor {

    private final Object key;

    private final RWLockRegistry lockRegistry;

    public static <T> T runWithWriteLocked(RWLockRegistry registry, Object key, Callable<T> callable) {
        final List<T> t = new ArrayList<>();
        RWWhileLockedProcessor processor = new RWWhileLockedProcessor(registry, key) {
            @Override
            protected void whileLocked() throws Exception {
                t.add(callable.call());
            }
        };
        try {
            processor.doWriteWhileLocked();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return t.get(0);
    }

    public static <T> T runWithReadLocked(RWLockRegistry lockRegistry, Object key, Callable<T> callable) {
        final List<T> t = new ArrayList<>();
        RWWhileLockedProcessor processor = new RWWhileLockedProcessor(lockRegistry, key) {
            @Override
            protected void whileLocked() throws Exception {
                t.add(callable.call());
            }
        };
        try {
            processor.doReadWhileLocked();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return t.get(0);
    }

    public static void runWithReadLocked(RWLockRegistry registry, Object key, Runnable runnable) {
        runWithReadLocked(registry, key, Executors.callable(runnable));
    }

    public static void runWithWriteLocked(RWLockRegistry registry, Object key, Runnable runnable) {
        runWithWriteLocked(registry, key, Executors.callable(runnable));
    }

    public RWWhileLockedProcessor(RWLockRegistry lockRegistry, Object key) {
        this.key = key;
        this.lockRegistry = lockRegistry;
    }


    public final void doReadWhileLocked() throws Exception {
        doWhileLocked(lockRegistry::obtainReadLock);
    }

    public final void doWriteWhileLocked() throws Exception {
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
