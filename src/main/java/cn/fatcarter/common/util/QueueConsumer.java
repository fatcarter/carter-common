package cn.fatcarter.common.util;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

@Slf4j
public abstract class QueueConsumer<T> {
    private final BlockingQueue<T> queue;
    private final Consumer<Exception> exceptionConsumer;
    private final Consumer<T> consumer;
    private final AtomicBoolean startup = new AtomicBoolean(false);

    @Setter
    private long delay;

    public QueueConsumer(BlockingQueue<T> queue, Consumer<T> consumer, Consumer<Exception> exceptionConsumer, long delay) {
        this.queue = queue;
        this.consumer = consumer;
        this.exceptionConsumer = exceptionConsumer;
        this.delay = delay;
    }

    public QueueConsumer(BlockingQueue<T> queue, Consumer<T> consumer, Consumer<Exception> exceptionConsumer) {
        this(queue, consumer, exceptionConsumer, 1000);
    }

    public QueueConsumer(BlockingQueue<T> queue, Consumer<T> consumer, long delay) {
        this(queue, consumer, QueueConsumer::throwsHandler, delay);
    }

    public QueueConsumer(BlockingQueue<T> queue, Consumer<T> consumer) {
        this(queue, consumer, QueueConsumer::throwsHandler);
    }


    public void startConsumer() throws InterruptedException {
        if (startup.compareAndSet(false, true)) {
            while (startup.get()) {
                try {
                    T obj = getObj(queue);
                    consumer.accept(obj);
                    delay();
                } catch (Exception e) {
                    triggerException(e);
                    if (e instanceof InterruptedException) {
                        throw new InterruptedException();
                    }
                }
            }
        }
    }

    public void stop() {
        startup.compareAndSet(true, false);
    }

    public void interrupt() {
        this.stop();
        Thread.currentThread().interrupt();
    }

    public boolean isStarted() {
        return startup.get();
    }


    protected void triggerException(Exception e) {
        try {
            exceptionConsumer.accept(e);
        } catch (Exception exception) {
            log.error("异常处理阶段, 抛出异常:" + e, e);
        }
    }

    protected void delay() throws InterruptedException {
        if (delay > 0) {
            Thread.sleep(delay);
        }
    }

    protected abstract T getObj(BlockingQueue<T> queue) throws InterruptedException;


    protected static void throwsHandler(Exception e) {
        log.error("Queue consumer error: " + e, e);
    }
}
