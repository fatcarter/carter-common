package cn.fatcarter.common.queue;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ConcurrencyTaskRunner {

    private final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    private final int concurrency;
    private final ExecutorService executorService;
    @Setter
    private boolean stopOnEmpty = true;

    public ConcurrencyTaskRunner(int concurrency) {
        this(concurrency, Executors.defaultThreadFactory());
    }

    public ConcurrencyTaskRunner(int concurrency, ThreadFactory threadFactory) {
        this.concurrency = concurrency;
        this.executorService = Executors.newFixedThreadPool(concurrency, threadFactory);
    }

    public ConcurrencyTaskRunner addTask(Runnable... runnables) {
        this.queue.addAll(Arrays.asList(runnables));
        return this;
    }

    public CompletableFuture<Void> run() {
        CompletableFuture<Void>[] futures = new CompletableFuture[this.concurrency];
        for (int i = 0; i < this.concurrency; i++) {
            futures[i] = CompletableFuture.runAsync(this::runTask, this.executorService);
        }
        return CompletableFuture.allOf(futures);
    }

    private void runTask() {
        while (true) {
            Runnable task;
            try {
                task = this.queue.poll(100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                break;
            }
            if (task == null) {
                if (this.stopOnEmpty) return;
                continue;
            }
            try {
                task.run();
            } catch (Throwable e) {
                log.error("执行任务异常! e={}", e, e);
            }
        }
    }

}
