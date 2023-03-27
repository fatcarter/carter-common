package cn.fatcarter.common.chain;

/**
 * 处理接口
 *
 * @author zhangyujia
 * @datetime 2020/10/20 3:44 下午
 */
public interface Handler<T> extends HandlerSuper {
    void handleRequest(T request, HandlerChain<T> chain) throws Exception;

}

