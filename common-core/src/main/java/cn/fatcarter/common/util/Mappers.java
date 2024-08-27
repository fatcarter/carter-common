package cn.fatcarter.common.util;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
//    public static <T> Function<T, Boolean> distinctByKey(Function<T, String> keyMapper) {
//        Set<String> exits = new HashSet<>();
//        return value -> {
//            String key = keyMapper.apply(value);
//            return exits.add(key);
//        };
//    }


}
