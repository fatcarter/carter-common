package cn.fatcarter.common.future;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@AllArgsConstructor
public class TypeRunnableFuture<T, V> implements RunnableFuture<V> {
    @Getter
    private FutureTask<V> original;
    @Getter
    private T type;

    public TypeRunnableFuture(Runnable runnable, V res, T type) {
        this(new FutureTask<>(runnable, res), type);
    }

    public TypeRunnableFuture(Runnable runnable, T type) {
        this(new FutureTask<>(runnable, null), type);
    }

    @Override
    public boolean isCancelled() {
        return original.isCancelled();
    }

    @Override
    public boolean isDone() {
        return original.isDone();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return original.cancel(mayInterruptIfRunning);
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return original.get();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return original.get(timeout, unit);
    }

    @Override
    public void run() {
        original.run();
    }
}
