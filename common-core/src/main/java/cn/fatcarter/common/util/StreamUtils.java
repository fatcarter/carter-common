package cn.fatcarter.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtils {
    public static <E, T> boolean mapNoneMatch(Collection<E> list, Function<? super E, T> mapper, Predicate<? super T> predicate) {
        return list.stream().map(mapper).noneMatch(predicate);
    }
    public static <E, T> boolean mapAnyMatch(Collection<E> list, Function<? super E, T> mapper, Predicate<? super T> predicate) {
        return list.stream().map(mapper).anyMatch(predicate);
    }

    public static <E> boolean noneMatch(Collection<E> list, Predicate<? super E> predicate) {
        return list.stream().noneMatch(predicate);
    }
    public static <E> boolean anyMatch(Collection<E> list, Predicate<? super E> predicate) {
        return list.stream().anyMatch(predicate);
    }
    public static <M  extends Map<K, V>,E, K, V> M toMap(Collection<E> list, Function<E, K> keyMapper, Function<E, V> valueMapper, BinaryOperator<V> mergeFunction,
                                            Supplier<M> creator) {
        return list.stream().collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction, creator));
    }

    public static <E, K, V> Map<K, V> toMap(Collection<E> list, Function<E, K> keyMapper, Function<E, V> valueMapper) {
        return toMap(list, keyMapper, valueMapper, throwingMerger(), HashMap::new);
    }

    public static <E, K> Map<K, E> toMap(Collection<E> list, Function<E, K> keyMapper) {
        return toMap(list, keyMapper, Function.identity());
    }


    public static <E, K> List<K> mapToList(Collection<E> list, Function<E, K> mapper) {
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    public static <E, K> Set<K> mapToSet(Collection<E> list, Function<E, K> mapper) {
        return list.stream().map(mapper).collect(Collectors.toSet());
    }

    public static <E, K> Map<K, List<E>> grouping(Collection<E> list, Function<E, K> mapper) {
        return list.stream().collect(Collectors.groupingBy(mapper));
    }

    public static <E> List<E> filter(Collection<E> list, Predicate<E> filter) {
        return list.stream().filter(filter).collect(Collectors.toList());
    }

    public static <E> Stream<E> filterStream(Collection<E> list, Predicate<E> filter) {
        return list.stream().filter(filter);
    }

    public static <E, T> List<T> filterMapToList(Collection<E> list, Predicate<E> filter, Function<E, T> mapper) {
        return list.stream().filter(filter).map(mapper).collect(Collectors.toList());
    }

    private static <T> BinaryOperator<T> throwingMerger() {
        return (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        };
    }
}
