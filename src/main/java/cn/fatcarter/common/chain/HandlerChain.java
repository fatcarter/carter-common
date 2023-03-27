package cn.fatcarter.common.chain;

/**
 * 责任链
 *
 * @author zhangyujia
 * @datetime 2020/10/20 3:44 下午
 */
public interface HandlerChain<T> {
    void doHandler(T request) throws Exception;

    int getIndex();
}
