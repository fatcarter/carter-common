package cn.fatcarter.common.chain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionHolder<T> {
    private Throwable throwable;
    private Handler<T> handler;
    private T request;
    private Integer index;
}
