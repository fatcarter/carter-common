package cn.fatcarter.common.util;

import cn.fatcarter.common.locks.DefaultLockRegistry;
import cn.fatcarter.common.locks.LockRegistry;
import cn.fatcarter.common.locks.WhileLockedProcessor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 节流器, 基于延迟队列实现
 */
@Slf4j
public abstract class Throttler {
    private static final ConcurrentMap<String, Throttle> MAP = new ConcurrentHashMap<>();
    private static DelayQueue<Throttle> queue = new DelayQueue<>();
    private static PollQueueConsumer<Throttle> consumer = new PollQueueConsumer<>(queue, Throttler::runThrottle);
    private static Thread runnerThread;
    private static final AtomicBoolean startup = new AtomicBoolean(false);
    private static ExecutorService executorService = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    private static LockRegistry lockRegistry = new DefaultLockRegistry();


    static {
        consumer.setBlockingOnNone(true);
    }

    public static void resetGlobalExecutorService(ExecutorService executorService) {
        Throttler.executorService = executorService;
    }

    public static void throttle(String key, Runnable runnable, Duration timeout) {
        start();
        Throttle throttle = MAP.get(key);
        if (throttle == null) {
            WhileLockedProcessor processor = new WhileLockedProcessor(lockRegistry, key) {
                @Override
                protected void whileLocked() throws Exception {
                    if (MAP.get(key) == null) {
                        Throttle t = new Throttle(getRunner(key, runnable), timeout);
                        MAP.put(key, t);
                        queue.add(t);
                    }
                }
            };
            try {
                processor.doWhileLocked();
            } catch (Exception e) {
                throw new RuntimeException("创建节流(throttle)对象失败! e=" + e, e);
            }
        }
    }


    private static Runnable getRunner(String key, Runnable runnable) {
        return () -> {
            try {
                runnable.run();
            } finally {
                MAP.remove(key);
            }
        };
    }

    private static void start() {
        if (startup.compareAndSet(false, true)) {
            runnerThread = new Thread(() -> {
                Thread.currentThread().setName("throttler-" + Incrementer.increment(Throttler.class.getName()));
                try {
                    consumer.startConsumer();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            });
            runnerThread.setDaemon(true);
            runnerThread.start();
            Runtime.getRuntime().addShutdownHook(new Thread(Throttler::stop, "ThrottlerShutdownHook"));
        }
    }

    private static void stop() {
        if (startup.compareAndSet(true, false)) {
            log.info("Stopping Throttler Thread");
            consumer.stop();
        }
    }

    private static void runThrottle(Throttle throttle) {
        if (throttle == null) return;

        Runnable runner = () -> {
            Runnable runnable = throttle.getRunnable();
            try {
                runnable.run();
            } catch (Throwable throwable) {
                log.error("run throttle runnable has an exception: " + throwable, throwable);
            }
        };

        if (executorService != null) {
            executorService.submit(runner);
        } else {
            runner.run();
        }

    }


    @Getter
    private static class Throttle implements Delayed {
        private Runnable runnable;
        private final long delayedTime;

        public Throttle(Runnable runnable, Duration timeout) {
            this.runnable = runnable;
            this.delayedTime = System.currentTimeMillis() + timeout.toMillis();
        }


        @Override
        public long getDelay(TimeUnit unit) {
            long c = delayedTime - System.currentTimeMillis();
            return unit.convert(c, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }
    }
}
