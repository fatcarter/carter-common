package cn.fatcarter.common.function;

import java.util.function.Function;

public interface RecursionFunction<T, R> {
    R apply(RecursionFunction<T, R> self, T t);

    static <T, R> Function<T, R> recursion(RecursionFunction<T, R> function) {
        return t -> function.apply(function, t);
    }
}
