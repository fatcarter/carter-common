package cn.fatcarter.common.function;

@FunctionalInterface
public interface Filter<T> {
    boolean accept(T t);
}
