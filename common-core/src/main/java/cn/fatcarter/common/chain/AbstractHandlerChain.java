package cn.fatcarter.common.chain;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zhangyujia
 * @datetime 2020/10/20 3:46 下午
 */
@Slf4j
public abstract class AbstractHandlerChain<T> implements HandlerChain<T>, Serializable {
    private static final long serialVersionUID = 1L;
    private Integer cursor;
    @Setter
    private ExceptionHandler<T> exceptionHandler;
    private final AtomicBoolean startup = new AtomicBoolean(false);

    public boolean skip(int count) {
        if (startup.get()) {
            return false;
        }
        if (cursor == null || cursor == getBaseCursor()) {
            cursor = count;
            return true;
        }
        return false;
    }

    @Override
    public void doHandler(T request) {
        if (startup.compareAndSet(false, true)) {
            if (cursor == null) cursor = getBaseCursor();
        }
        List<? extends Handler<T>> handlers = getHandlers();
        if (handlers == null || handlers.isEmpty()) return;
        if (cursor < handlers.size()) {
            Handler<T> handler = handlers.get(cursor++);
            handleRequest(handler, request);
        }
    }

    @Override
    public int getIndex() {
        return cursor == null ? getBaseCursor() : cursor;
    }

    private void handleRequest(Handler<T> handler, T request) {
        try {
            handler.handleRequest(request, this);
        } catch (Exception e) {
            triggerException(handler, request, e);
        }
    }

    protected void triggerException(Handler<T> handler, T request, Throwable t) {
        if (exceptionHandler == null) {
            log.error("Handler[" + handler + "] handle request error! request=" + request + ", e=" + t, t);
        } else {
            exceptionHandler.handleException(ExceptionHolder.<T>builder()
                    .handler(handler)
                    .request(request)
                    .throwable(t)
                    .index(cursor - 1)
                    .build(), this);
        }
    }


    protected abstract List<? extends Handler<T>> getHandlers();

    protected int getBaseCursor() {
        return 0;
    }
}
