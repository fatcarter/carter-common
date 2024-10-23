//package cn.fatcarter.common.future;
//
//import lombok.Setter;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Callable;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.FutureTask;
//import java.util.concurrent.RunnableFuture;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class AsyncExecutor extends CompletableFuture<Void> implements RunnableFuture<Void> {
//    @Setter
//    private ExecutorService executor = Executors.newSingleThreadExecutor();
//    private final List<FutureTask<?>> futures = new ArrayList<>();
//    private AtomicInteger finishCount = new AtomicInteger();
//
//    public AsyncExecutor() {
//    }
//
//
//    public <T> Future<T> supplyAsync(Callable<T> supplier) {
//        FutureTask<T> future = new FutureTask<>(() -> this.call(supplier));
//        this.futures.add(future);
//        return future;
//    }
//
//    @Override
//    public void run() {
//        for (FutureTask<?> future : this.futures) {
//            executor.execute(future);
//        }
//    }
//
//    private <T> T call(Callable<T> callable) throws Exception {
//        try {
//            return callable.call();
//        } finally {
//            int count = this.finishCount.incrementAndGet();
//            if (count == futures.size()) {
//                super.complete(null);
//            }
//        }
//    }
//}