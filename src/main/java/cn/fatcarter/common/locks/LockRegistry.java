package cn.fatcarter.common.locks;

import java.util.concurrent.locks.Lock;

public interface LockRegistry {

    Lock obtain(Object lockKey);
}
