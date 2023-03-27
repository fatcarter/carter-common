package cn.fatcarter.common.locks.readwrite;

import cn.fatcarter.common.locks.PassThruLock;

import java.util.concurrent.locks.Lock;

public class PassThruRWLockRegistry implements RWLockRegistry{
    @Override
    public Lock obtainReadLock(Object lockKey) {
        return new PassThruLock();
    }

    @Override
    public Lock obtainWriteLock(Object lockKey) {
        return new PassThruLock();
    }
}
