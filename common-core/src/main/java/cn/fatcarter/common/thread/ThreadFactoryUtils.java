package cn.fatcarter.common.thread;

import lombok.NonNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadFactoryUtils {

    public static ThreadFactory createThreadFactory(String format) {
        return new ThreadFactory() {
            private final ThreadGroup group = Thread.currentThread().getThreadGroup();
            private final Integer priority = Thread.currentThread().getPriority();
            private final AtomicInteger id = new AtomicInteger(0);

            @Override
            public Thread newThread(@NonNull Runnable r) {
                String threadName = String.format(format, id.getAndIncrement());
                Thread t = new Thread(group, r, threadName);
                t.setPriority(priority);
                return t;
            }
        };
    }
}
