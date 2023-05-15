package cn.fatcarter.common.chain;

@FunctionalInterface
public interface ExceptionHandler<T> {
    void handleException(ExceptionHolder<T> holder, HandlerChain<T> chain);
}
