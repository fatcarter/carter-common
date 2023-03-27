package cn.fatcarter.common.locks;

import java.util.concurrent.locks.Lock;

/**
 * 无锁实现
 */
public class PassThruLockRegistry implements LockRegistry {

    @Override
    public Lock obtain(Object lockKey) {
        return new PassThruLock();
    }

}
