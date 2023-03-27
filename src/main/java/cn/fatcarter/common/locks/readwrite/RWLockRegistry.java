package cn.fatcarter.common.locks.readwrite;

import java.util.concurrent.locks.Lock;

public interface RWLockRegistry {
    Lock obtainReadLock(Object lockKey);

    Lock obtainWriteLock(Object lockKey);
}
