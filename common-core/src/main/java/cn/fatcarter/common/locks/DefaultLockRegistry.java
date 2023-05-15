package cn.fatcarter.common.locks;


import cn.fatcarter.common.util.Assert;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DefaultLockRegistry implements LockRegistry {

    private final Lock[] lockTable;

    private final int mask;

    /**
     * Constructs a DefaultLockRegistry with the default
     * mask 0xFF with 256 locks.
     */
    public DefaultLockRegistry() {
        this(0xFF);
    }

    /**
     * Constructs a DefaultLockRegistry with the supplied
     * mask - the mask must have a value Math.pow(2, n) - 1 where n
     * is 1 to 31, creating a hash of Math.pow(2, n) locks.
     * <p> Examples:
     * <ul>
     * <li>0x3ff (1023) - 1024 locks</li>
     * <li>0xfff (4095) - 4096 locks</li>
     * </ul>
     *
     * @param mask The bit mask.
     */
    public DefaultLockRegistry(int mask) {
        String bits = Integer.toBinaryString(mask);
        Assert.isTrue(bits.length() < 32 && (mask == 0 || bits.lastIndexOf('0') < bits.indexOf('1')), "Mask must be a power of 2 - 1");
        this.mask = mask;
        int arraySize = this.mask + 1;
        this.lockTable = new ReentrantLock[arraySize];
        for (int i = 0; i < arraySize; i++) {
            this.lockTable[i] = new ReentrantLock();
        }
    }

    /**
     * Obtains a lock by masking the lockKey's hashCode() with
     * the mask and using the result as an index to the lock table.
     *
     * @param lockKey the object used to derive the lock index.
     */
    @Override
    public Lock obtain(Object lockKey) {
        Assert.notNull(lockKey, "'lockKey' must not be null");
        Integer lockIndex = lockKey.hashCode() & this.mask;
        return this.lockTable[lockIndex];
    }
}
