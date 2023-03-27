package cn.fatcarter.common.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class PassThruLock implements Lock {

    public PassThruLock() {
        super();
    }

    @Override
    public void unlock() {
        // noop
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        return true;
    }

    @Override
    public boolean tryLock() {
        return true;
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException("This method is not supported for this implementation of Lock");
    }

    @Override
    public void lockInterruptibly() {
        // noop
    }

    @Override
    public void lock() {
        // noop
    }
}
