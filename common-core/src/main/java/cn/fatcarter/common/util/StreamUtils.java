package cn.fatcarter.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtils {


    public static <E, T> boolean mapNoneMatch(Collection<E> list, Function<? super E, T> mapper, Predicate<? super T> predicate) {
        return CollectionUtils.isEmpty(list) || list.stream().map(mapper).noneMatch(predicate);
    }
    public static <E, T> boolean mapAnyMatch(Collection<E> list, Function<? super E, T> mapper, Predicate<? super T> predicate) {
        return CollectionUtils.isNotEmpty(list) && list.stream().map(mapper).anyMatch(predicate);
    }

    public static <E, T> boolean mapAllMatch(Collection<E> list, Function<? super E, T> mapper, Predicate<? super T> predicate) {
        return CollectionUtils.isEmpty(list) || list.stream().map(mapper).allMatch(predicate);
    }


    public static <E> boolean allMatch(Collection<E> list, Predicate<? super E> predicate) {
        return CollectionUtils.isEmpty(list) || list.stream().allMatch(predicate);
    }

    public static <E> boolean noneMatch(Collection<E> list, Predicate<? super E> predicate) {
        return CollectionUtils.isEmpty(list) || list.stream().noneMatch(predicate);
    }

    public static <E> boolean anyMatch(Collection<E> list, Predicate<? super E> predicate) {
        return CollectionUtils.isNotEmpty(list) && list.stream().anyMatch(predicate);
    }

    public static <M  extends Map<K, V>,E, K, V> M toMap(Collection<E> list, Function<E, K> keyMapper, Function<E, V> valueMapper, BinaryOperator<V> mergeFunction,
                                            Supplier<M> creator) {
        return list.stream().collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction, creator));
    }

    public static <M extends Map<K, V>, E, K, V> M toMap(Collection<E> list, Function<E, K> keyMapper, Function<E, V> valueMapper, Supplier<M> creator) {
        return list.stream().collect(Collectors.toMap(keyMapper, valueMapper, throwingMerger(), creator));
    }

    public static <E, K, V> Map<K, V> toMap(Collection<E> list, Function<E, K> keyMapper, Function<E, V> valueMapper, BinaryOperator<V> mergeFunction) {
        return toMap(list, keyMapper, valueMapper, mergeFunction, HashMap::new);
    }

    public static <E, K, V> Map<K, V> toMap(Collection<E> list, Function<E, K> keyMapper, Function<E, V> valueMapper) {
        return toMap(list, keyMapper, valueMapper, () -> new HashMap<>());
    }

    public static <E, K> Map<K, E> toMap(Collection<E> list, Function<E, K> keyMapper, BinaryOperator<E> mergeFunc) {
        return toMap(list, keyMapper, Function.identity(), mergeFunc);
    }

    public static <E, K> Map<K, E> toMap(Collection<E> list, Function<E, K> keyMapper) {
        return toMap(list, keyMapper, Function.identity());
    }

    public static <E, K> Stream<K> mapToStream(Collection<E> list, Function<E, K> mapper) {
        return list.stream().map(mapper);
    }

    public static <E, K> List<K> mapToList(Collection<E> list, Function<E, K> mapper) {
        return mapToStream(list, mapper).collect(Collectors.toList());
    }

    public static <E, K> List<K> mapToListDistinct(Collection<E> list, Function<E, K> mapper) {
        return new ArrayList<>(mapToSet(list, mapper));
    }

    public static <E, K> Set<K> mapToSet(Collection<E> list, Function<E, K> mapper) {
        return mapToStream(list, mapper).collect(Collectors.toSet());
    }

    public static <E, K, V> Map<K, V> grouping(Collection<E> list, Function<E, K> keyMapper, Collector<? super E, ?, V> downstream) {
        return list.stream().collect(Collectors.groupingBy(keyMapper, downstream));
    }

    public static <E, K, V> Map<K, V> grouping(Collection<E> list, Function<E, K> keyMapper, Function<E, V> valueMapper, Collector<? super V, ?, V> downstream) {
        return grouping(list, keyMapper, Collectors.mapping(valueMapper, downstream));
    }

    public static <E, K, V> Map<K, List<V>> grouping(Collection<E> list, Function<E, K> keyMapper, Function<E, V> valueMapper) {
        return grouping(list, keyMapper, Collectors.mapping(valueMapper, Collectors.toList()));
    }

    public static <E, K> Map<K, List<E>> grouping(Collection<E> list, Function<E, K> keyMapper) {
        return grouping(list, keyMapper, Function.identity());
    }

    public static <E> List<E> filter(Collection<E> list, Predicate<E> filter) {
        return list.stream().filter(filter).collect(Collectors.toList());
    }

    public static <E> Stream<E> filterStream(Collection<E> list, Predicate<E> filter) {
        return list.stream().filter(filter);
    }

    public static <E, T> Set<T> mapFilterToSet(Collection<E> list,Function<E, T> mapper, Predicate<T> filter) {
        return list.stream().map(mapper).filter(filter).collect(Collectors.toSet());
    }

    public static <E, T> Set<T> filterMapToSet(Collection<E> list, Predicate<E> filter, Function<E, T> mapper) {
        return list.stream().filter(filter).map(mapper).collect(Collectors.toSet());
    }

    public static <E, T> List<T> mapFilterToList(Collection<E> list,Function<E, T> mapper, Predicate<T> filter) {
        return list.stream().map(mapper).filter(filter).collect(Collectors.toList());
    }

    public static <E, T> List<T> filterMapToList(Collection<E> list, Predicate<E> filter, Function<E, T> mapper) {
        return list.stream().filter(filter).map(mapper).collect(Collectors.toList());
    }

    public static <T> T max(Collection<T> list, Comparator<T> comparator) {
        return list.stream().max(comparator).orElse(null);
    }

    public static <T> T max(Collection<T> list, Comparator<T> comparator,T defaultValue) {
        return list.stream().max(comparator).orElse(defaultValue);
    }
    public static <T> T min(Collection<T> list, Comparator<T> comparator) {
        return list.stream().min(comparator).orElse(null);
    }

    public static <T> T min(Collection<T> list, Comparator<T> comparator,T defaultValue) {
        return list.stream().min(comparator).orElse(defaultValue);
    }

    public static <T> Optional<T> findFirst(Collection<T> list, Predicate<T> filter) {
        return list.stream().filter(filter).findFirst();
    }

    public static <T> Optional<T> findFirst(T[] list, Predicate<T> filter) {
        if (list == null || list.length == 0) {
            return Optional.empty();
        }
        return Arrays.stream(list).filter(filter).findFirst();
    }


    public static <T> List<T> distinct(Collection<T> list) {
        return distinct(list, Function.identity());
    }


    public static <T, V> List<T> distinct(Collection<T> list, Function<T, V> valueMapper) {
        Set<V> exists = new HashSet<>();
        return list.stream().filter(item -> exists.add(valueMapper.apply(item))).collect(Collectors.toList());
    }

    @SafeVarargs
    public static <T> List<T> distinct(T first, T... others) {
        if (others == null || others.length == 0) {
            return new ArrayList<>(Collections.singletonList(first));
        }
        List<T> list = new ArrayList<>();
        list.add(first);
        list.addAll(Arrays.asList(others));
        return distinct(list, Function.identity());
    }


    public static <T> BinaryOperator<T> throwingMerger() {
        return Mergers.throwMerger();
    }

}
