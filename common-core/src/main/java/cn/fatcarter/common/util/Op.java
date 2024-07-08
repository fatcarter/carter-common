package cn.fatcarter.common.util;

import cn.fatcarter.common.function.Predicates;

import java.util.Collection;
import java.util.Map;
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

    public static <T> Optional<T> n(T t) {
        return opn(t);
    }
    public static <T> Optional<T> opn(T t) {
        return Optional.ofNullable(t);
    }

    public static <T> Optional<Collection<T>> notEmpty(Collection<T> collection) {
        return n(collection).filter(Predicates.not(Collection::isEmpty));
    }

    public static <K, V> Optional<Map<K, V>> notEmpty(Map<K, V> map) {
        return n(map).filter(Predicates.not(Map::isEmpty));
    }


    public static <T> Optional<T> op(T t) {
        return of(t);
    }

    public static <T> Optional<T> o(T t) {
        return of(t);
    }

    public static <T> Optional<T> of(T t) {
        return Optional.of(t);
    }


    public static <T> Optional<T> e() {
        return Optional.empty();
    }
}
