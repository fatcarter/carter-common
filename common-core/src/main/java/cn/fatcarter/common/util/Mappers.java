package cn.fatcarter.common.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class Mappers {

    public static <T, M, R> Function<T, R> andThen(Function<T, M> mapper, Function<M, R> next) {
        return mapper.andThen(next);
    }

    public static <T> Function<List<T>, Optional<T>> firstMapper() {
        return list -> list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }
    public static <T> Function<List<T>, Optional<T>> firstMapper(Predicate<T> predicate) {
        return list -> StreamUtils.findFirst(list, predicate);
    }

    public static <T, R> Function<List<T>, List<R>> listMapMapper(Function<T, R> valueMapper) {
        return list -> StreamUtils.mapToList(list, valueMapper);
    }

    public static <T, K> Function<List<T>, Map<K, T>> toMapMapper(Function<T, K> keyMapper) {
        return toMapMapper(keyMapper, Function.identity());
    }

    public static <T, K> Function<List<T>, Map<K, List<T>>> groupingMapper(Function<T, K> keyMapper) {
        return list -> StreamUtils.grouping(list, keyMapper);
    }

    public static <T, K, V> Function<List<T>, Map<K, V>> toMapMapper(Function<T, K> keyMapper, Function<T, V> valueMapper) {
        return list -> StreamUtils.toMap(list, keyMapper, valueMapper);
    }

    public static <T> Function<Collection<T>, List<T>> listFilter(Predicate<T> predicate) {
        return list -> StreamUtils.filter(list, predicate);
    }

    public static <T, R> Function<List<T>, List<R>> mapMapper(Function<T, R> mapper) {
        return list -> StreamUtils.mapToList(list, mapper);
    }

    public static <T> Function<List<T>, List<T>> distinctByKey(Function<T, String> keyMapper) {
        Set<String> exits = new HashSet<>();
        Predicate<T> predicate = item -> {
            String key = keyMapper.apply(item);
            return exits.add(key);
        };
        return value -> value.stream().filter(predicate).toList();
    }
}
