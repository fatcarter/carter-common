package cn.fatcarter.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MapUtils {
    public static <K, V, K1> Map<K1, V> typeMap(Map<K, V> src, Function<K, K1> keyMapper) {
        return typeMap(src, keyMapper, UnaryOperator.identity());
    }

    public static <K, V, K1, V1> Map<K1, V1> typeMap(Map<K, V> src, Function<K, K1> keyMapper, Function<V, V1> valueMapper) {
        return typeMap(src, keyMapper, valueMapper, StreamUtils.throwingMerger(), HashMap::new);
    }

    public static <K, V, K1, V1> Map<K1, V1> typeMap(Map<K, V> src, Function<K, K1> keyMapper, Function<V, V1> valueMapper, BinaryOperator<V1> merging, Supplier<Map<K1, V1>> mapSupplier) {
        return StreamUtils.toMap(src.keySet(), keyMapper, (K k) -> Op.opn(src.get(k)).map(valueMapper).orElse(null), merging, mapSupplier);
    }
}
