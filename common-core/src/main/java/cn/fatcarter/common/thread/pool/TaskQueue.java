package cn.fatcarter.common.thread.pool;

import lombok.Setter;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 自定义任务队列,当运行中的线程小于核心线程数量,加入队列等待worker处理,当大于核心线程数量但是小于最大数量时offer方法返回false,指示executor应该创建worker而不是将任务添加到队列中
 *
 * @param <T>
 */
@Setter
public final class TaskQueue<T extends Runnable> extends LinkedBlockingQueue<Runnable> {

    private EdgerThreadPoolExecutor executor;

    public TaskQueue(int capacity) {
        super(capacity);
    }

    @Override
    public boolean offer(Runnable runnable) {
        if (executor == null) {
            throw new RejectedExecutionException("The task queue does not have executor!");
        }

        int currentPoolThreadSize = executor.getPoolSize();
        // have free worker. put task into queue to let the worker deal with task.
        if (executor.getActiveCount() < currentPoolThreadSize) {
            return super.offer(runnable);
        }

        // return false to let executor create new worker.
        if (currentPoolThreadSize < executor.getMaximumPoolSize()) {
            return false;
        }

        // currentPoolThreadSize >= max
        return super.offer(runnable);
    }

    /**
     * retry offer task
     *
     * @param o task
     * @return offer success or not
     * @throws RejectedExecutionException if executor is terminated.
     */
    public boolean retryOffer(Runnable o, long timeout, TimeUnit unit) throws InterruptedException {
        if (executor.isShutdown()) {
            throw new RejectedExecutionException("Executor is shutdown!");
        }
        return super.offer(o, timeout, unit);
    }
}
