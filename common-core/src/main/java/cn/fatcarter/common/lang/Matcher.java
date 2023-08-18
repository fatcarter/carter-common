package cn.fatcarter.common.lang;

@FunctionalInterface
public interface Matcher<T> {
    boolean match(T t);
}
