package cn.fatcarter.common.util;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class Op {
    public static <S, T> T map(S s, Function<S, T> mapper) {
        return map(s, mapper, (T) null);
    }

    public static <S, T> T map(S s, Function<S, T> mapper, T def) {
        return Optional.ofNullable(s).map(mapper).orElse(def);
    }

    public static <S, T> T map(S s, Function<S, T> mapper, Supplier<T> supplier) {
        return Optional.ofNullable(s).map(mapper).orElseGet(supplier);
    }

    public static <T> Optional<T> opn(T t) {
        return Optional.ofNullable(t);
    }

    public static <T> Optional<T> op(T t) {
        return Optional.of(t);
    }


}
