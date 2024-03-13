package cn.fatcarter.common.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtils {

    public static ThreadPoolExecutor createExecutor(ThreadPoolConfig config) {
        BlockingQueue<Runnable> queue = config.getQueueSize() <= 0 ?
                new LinkedBlockingQueue<>() : new ArrayBlockingQueue<>(config.getQueueSize());

        ThreadFactory factory = config.getThreadNameFormat() != null ? ThreadFactoryUtils.createThreadFactory(config.getThreadNameFormat())
                : Executors.defaultThreadFactory();

        RejectedExecutionHandler handler = config.getRejectedExecutionHandler() == null ?
                new ThreadPoolExecutor.AbortPolicy() : config.getRejectedExecutionHandler();
        return new ThreadPoolExecutor(
                config.getCorePoolSize(), config.getMaxPoolSize(),
                config.getKeepAliveMillis(), TimeUnit.MILLISECONDS,
                queue, factory, handler
        );

    }
}
