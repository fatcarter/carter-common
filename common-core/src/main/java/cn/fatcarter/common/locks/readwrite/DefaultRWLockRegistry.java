package cn.fatcarter.common.locks.readwrite;

import cn.fatcarter.common.util.Assert;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DefaultRWLockRegistry implements RWLockRegistry {
    private ReadWriteLock[] lockTable;
    private int mask;
    private boolean fair;

    public DefaultRWLockRegistry() {
        this(255, false);
    }

    public DefaultRWLockRegistry(int mask,boolean fair) {
        String bits = Integer.toBinaryString(mask);
        this.fair = fair;
        Assert.isTrue(bits.length() < 32 && (mask == 0 || bits.lastIndexOf('0') < bits.indexOf('1')), "Mask must be a power of 2 - 1");
        this.mask = mask;
        int arraySize = this.mask + 1;
        this.lockTable = new ReentrantReadWriteLock[arraySize];
        for (int i = 0; i < arraySize; i++) {
            this.lockTable[i] = createReadWriteLock();
        }
    }


    @Override
    public Lock obtainReadLock(Object lockKey) {
        return getLock(lockKey).readLock();
    }

    @Override
    public Lock obtainWriteLock(Object lockKey) {
        return getLock(lockKey).writeLock();
    }

    private ReadWriteLock getLock(Object lockKey) {
        Assert.notNull(lockKey, "'lockKey' must not be null");
        Integer lockIndex = lockKey.hashCode() & this.mask;
        return this.lockTable[lockIndex];
    }

    private ReadWriteLock createReadWriteLock(){
        return new ReentrantReadWriteLock(fair);
    }
}
