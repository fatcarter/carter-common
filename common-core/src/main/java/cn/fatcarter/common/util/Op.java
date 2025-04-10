package cn.fatcarter.common.util;

import cn.fatcarter.common.function.Predicates;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Op {
    public static <T> T orNull(T t) {
        return Op.n(t).orElse(null);
    }

    public static <T> T orElse(T t, Supplier<? extends T> getter) {
        return Op.n(t).orElseGet(getter);
    }

    public static <T> T orElse(T t, T def) {
        return Op.n(t).orElse(def);
    }

    public static <S> String mapStr(S s, Function<S, String> mapper) {
        return mapStr(s, mapper, StringUtils.EMPTY);
    }
    public static <S> String mapStr(S s ,Function<S,String> mapper,String def){
        return map(s, mapper, def);
    }

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

    public static <T> Optional<Collection<T>> withoutNull(Collection<T> collection) {
        return without(collection, Objects::nonNull);
    }

    public static <T> Optional<Collection<String>> withoutBlank(Collection<String> collection) {
        return without(collection, StringUtils::isNotBlank);
    }

    public static <T> Optional<Collection<T>> without(Collection<T> collection, Predicate<T> predicate) {
        return notEmpty(flatMap(collection).filter(predicate).toList());
    }

    public static <T> Optional<Collection<T>> keep(Collection<T> collection, Predicate<T> predicate) {
        return without(collection, predicate.negate());
    }

    public static <T> Stream<T> flatMap(Collection<T> collection) {
        return notEmpty(collection).stream().flatMap(Collection::stream);
    }

    public static <T> Optional<T> findAny(Collection<T> collection) {
        return flatMap(collection).findAny();
    }

    public static <T> Optional<T> findFirst(Collection<T> collection) {
        return flatMap(collection).findFirst();
    }

    public static <K, V> Optional<Map<K, V>> notEmpty(Map<K, V> map) {
        return n(map).filter(Predicates.not(Map::isEmpty));
    }

    public static <T> Optional<String> notBlank(String value){
        return n(value).filter(StringUtils::isNotBlank);
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

    public static <T> boolean test(T value,Predicate<T> predicate) {
        return test(value, predicate, false);
    }

    public static <T> boolean test(T value,Predicate<T> predicate,boolean def) {
        return n(value).map(predicate::test).orElse(def);
    }


    public static <T> Optional<T> e() {
        return Optional.empty();
    }
}
