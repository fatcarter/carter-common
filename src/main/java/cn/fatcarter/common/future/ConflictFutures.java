package cn.fatcarter.common.future;

import cn.fatcarter.common.locks.DefaultLockRegistry;
import cn.fatcarter.common.locks.LockRegistry;
import cn.fatcarter.common.locks.WhileLockedProcessor;
import cn.fatcarter.common.locks.readwrite.DefaultRWLockRegistry;
import cn.fatcarter.common.locks.readwrite.RWLockRegistry;
import cn.fatcarter.common.locks.readwrite.RWWhileLockedProcessor;
import cn.fatcarter.common.log.Logging;
import cn.fatcarter.common.log.Slf4jLogging;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

@Slf4j
public class ConflictFutures {
    private final LockRegistry lockRegistry;
    private final ConcurrentMap<String, KeyFuture> map;
    private final ExecutorService executorService;
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = new ScheduledThreadPoolExecutor(10);
    private final RWLockRegistry rw = new DefaultRWLockRegistry();
    private final Object lock = new Object();
    private final Logging logger = new Slf4jLogging(log);

    /**
     * 创建一个对象
     *
     * @param registry        锁注册器
     * @param executorService 执行器允许为null当为null时, 将有调用线程执行future
     * @param capacity        key容量
     */
    public ConflictFutures(LockRegistry registry, ExecutorService executorService, int capacity) {
        this.lockRegistry = registry;
        this.executorService = executorService;
        this.map = new ConcurrentHashMap<>(capacity);
        SCHEDULED_EXECUTOR_SERVICE.scheduleWithFixedDelay(this::clearFutures, 5, 1, TimeUnit.SECONDS);
    }

    public ConflictFutures(ExecutorService executorService, int capacity) {
        this(new DefaultLockRegistry(), executorService, capacity);
    }

    public ConflictFutures(ExecutorService executorService) {
        this(new DefaultLockRegistry(), executorService, 32);
    }


    public void run(String key, RunnableFuture<?> future, BiFunction<String, RunnableFuture<?>, Boolean> onConflictDiscard) {
        Runnable runnable = () -> {
            logger.trace("Run new future with key [{}]", key);
            KeyFuture kf = map.get(key);
            logger.trace("Map get future [{}] with key [{}]", kf, key);
            if (kf != null) {
                if (!kf.isDone()) {
                    logger.info("Future state is running! Will execute conflict strategy!");
                    if (onConflictDiscard.apply(key, kf.getFuture())) {
                        logger.info("Future conflict, will discard new future!");
                        return;
                    } else {
                        logger.debug("Future conflict, will replace use new future!");
                    }
                }
            }
            logger.debug("Create new Future with key [{}]", key);
            KeyFuture newFuture = new KeyFuture(key, future);
            logger.trace("New future [{}] with key: {}", future, key);
            map.put(key, newFuture);
            runFuture(newFuture);
        };
        RWWhileLockedProcessor.runWithReadLocked(rw, lock, () -> {
            WhileLockedProcessor.runWithLocked(lockRegistry, key, runnable);
        });
    }

    private void runFuture(KeyFuture future) {
        if (executorService == null) {
            logger.debug("Run future [{}] with key [{}] on caller thread!", future.getFuture(), future.getKey());
            future.run();
        } else {
            logger.debug("Run future [{}] with key [{}] on executor service [{}]", future.getFuture(), future.getKey(), executorService);
            executorService.submit(future::run);
        }
    }

    private void clearFutures() {
        logger.debug("Will clean futures in {}", this);
        if (map.isEmpty()) {
            log.debug("Futures is empty");
            return;
        }
        logger.debug("Will clean future with write locked! ");
        RWWhileLockedProcessor.runWithWhiteLocked(rw, lock, () -> {
            logger.debug("Locked!! Clean futures");
            ArrayList<String> keys = new ArrayList<>();
            for (KeyFuture value : map.values()) {
                if (value.isDone() || value.isCancelled()) {
                    keys.add(value.getKey());
                }
            }
            logger.debug("Clean futures: {}", keys.toString());
            keys.forEach(map::remove);
            logger.debug("Features clean {} finished ", keys.size());
        });
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class KeyFuture {
        private String key;
        private RunnableFuture<?> future;

        public boolean isCancelled() {
            return future.isCancelled();
        }

        public boolean isDone() {
            return future.isDone();
        }

        public void run() {
            this.future.run();
        }
    }
}
