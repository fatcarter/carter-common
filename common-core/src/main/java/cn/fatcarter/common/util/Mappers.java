package cn.fatcarter.common.util;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class Mappers {
    public static <T, K> Function<List<T>, Map<K, T>> toMapMapper(Function<T, K> keyMapper) {
        return toMapMapper(keyMapper, Function.identity());
    }

    public static <T, K, V> Function<List<T>, Map<K, V>> toMapMapper(Function<T, K> keyMapper, Function<T, V> valueMapper) {
        return list -> StreamUtils.toMap(list, keyMapper, valueMapper);
    }

//    public static <T> Predicate<Collection<T>> filter(Predicate<T> predicate) {
//        return list -> StreamUtils.filter(list, predicate);
//    }
//
    public static <T> Function<List<T>, List<T>> distinctByKey(Function<T, String> keyMapper) {
        Set<String> exits = new HashSet<>();
        Predicate<T> predicate = item -> {
            String key = keyMapper.apply(item);
            return exits.add(key);
        };
        return value -> value.stream().filter(predicate).toList();
    }
}
