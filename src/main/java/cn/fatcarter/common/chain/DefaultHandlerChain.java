package cn.fatcarter.common.chain;


import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

public class DefaultHandlerChain<T> extends AbstractHandlerChain<T> {
    @Singular
    private final List<Handler<T>> handlers;

    @Override
    protected List<Handler<T>> getHandlers() {
        return handlers;
    }

    public DefaultHandlerChain(List<Handler<T>> handlers, ExceptionHandler<T> exceptionHandler, Integer skip) {
        this.handlers = handlers;
        super.setExceptionHandler(exceptionHandler);
        if (skip != null) {
            super.skip(skip);
        }
    }
    public static <T> Builder<T> builder(){
        return new Builder<>();
    }

    public static class Builder<T> {
        private List<Handler<T>> handlers;
        private ExceptionHandler<T> exceptionHandler;
        private Integer skip;

        public Builder<T> handler(Handler<T> handler) {
            if (handlers == null) handlers = new ArrayList<>();
            if (handler != null) handlers.add(handler);
            return this;
        }

        public Builder<T> handlers(List<? extends Handler<T>> handlers) {
            if (handlers != null) this.handlers = new ArrayList<>(handlers);
            return this;
        }

        public Builder<T> skip(int skip) {
            this.skip = skip;
            return this;
        }

        public Builder<T> exceptionHandler(ExceptionHandler<T> handler) {
            this.exceptionHandler = handler;
            return this;
        }

        public DefaultHandlerChain<T> build() {
            return new DefaultHandlerChain<>(handlers, exceptionHandler, skip);
        }
    }

}
