package cn.fatcarter.common.rpc.dubbo;

import cn.fatcarter.common.reflect.ReflectUtils;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.Invoker;
import org.springframework.lang.Nullable;

import java.lang.reflect.Field;

public class DubboService<T> {
    private final ReferenceConfig<T> config;
    private final T instance;
    private volatile Invoker<?> invoker;

    public DubboService(ReferenceConfig<T> config) {
        this.config = config;
        this.instance = config.get(false);
        resolveInvoker();
    }

    @Nullable
    public T getService() {
        if (invoker == null) {
            throw new IllegalStateException("invoker is null!");
        }
        if (invoker.isAvailable()) {
            return instance;
        }
        return null;
    }

    private void resolveInvoker() {
        if (invoker == null) {
            Field field = ReflectUtils.getField(ReferenceConfig.class, "invoker");
            this.invoker = (Invoker<?>) ReflectUtils.getFieldValue(config, field);
        }
    }
}
