package cn.fatcarter.common.util;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Slf4j
public class PollQueueConsumer<T> extends QueueConsumer<T> {
    public PollQueueConsumer(BlockingQueue<T> queue, Consumer<T> consumer, Consumer<Exception> exceptionConsumer, long delay) {
        super(queue, consumer, exceptionConsumer, delay);
    }

    public PollQueueConsumer(BlockingQueue<T> queue, Consumer<T> consumer, Consumer<Exception> exceptionConsumer) {
        super(queue, consumer, exceptionConsumer);
    }

    public PollQueueConsumer(BlockingQueue<T> queue, Consumer<T> consumer, long delay) {
        super(queue, consumer, delay);
    }

    public PollQueueConsumer(BlockingQueue<T> queue, Consumer<T> consumer) {
        super(queue, consumer);
    }


    @Setter
    private Duration blockingTimeout;
    @Setter
    private boolean blockingOnNone;


    @Override
    protected T getObj(BlockingQueue<T> queue) throws InterruptedException {
        try {
            if (blockingOnNone) {
                if (blockingTimeout != null) {
                    return queue.poll(blockingTimeout.toMillis(), TimeUnit.MILLISECONDS);
                } else {
                    return queue.take();
                }
            } else {
                return queue.poll();
            }
        } catch (InterruptedException e) {
            log.error("Thread interrupted when queue poll: " + e, e);
            throw e;
        }
    }

}
