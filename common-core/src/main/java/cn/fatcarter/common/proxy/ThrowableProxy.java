package cn.fatcarter.common.proxy;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ThrowableProxy implements InvocationHandler {
    private Object subject;
    @Setter
    private Consumer<Throwable> exceptionHandler;
    @Setter
    private Object defaultValue;

    private static final ConcurrentHashMap<String, Object> IGNORE_PROXY = new ConcurrentHashMap<>();

    public ThrowableProxy(Object subject) {
        this(subject, null, null);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(subject, args);
        } catch (Throwable e) {
            if (this.exceptionHandler != null) {
                this.exceptionHandler.accept(e);
            } else {
                log.error("调用接口失败! class={},method={},args={},error={}", this.subject.getClass(),
                        method.getName(), Arrays.toString(args), e, e);
            }
        }
        return this.defaultValue;
    }

    @SuppressWarnings("unchecked")
    public static <T> T ignoreThrowable(T subject) {
        Class<?> clz = unboxProxy(subject.getClass());
        return (T) IGNORE_PROXY.computeIfAbsent(clz.toString(), name -> {
            return Proxy.newProxyInstance(clz.getClassLoader(), clz.getInterfaces(), new ThrowableProxy(subject));
        });
    }

    private static <T> Class<T> unboxProxy(Class<T> clz) {
        return Proxy.isProxyClass(clz) ? unboxProxy(clz) : clz;
    }
}
