package cn.fatcarter.common.function;

import cn.fatcarter.common.util.StringUtils;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Predicates {
    private Predicates() {
    }


    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }

    public static <T, R> Predicate<T> notNull(Function<T, R> mapper) {
        return mapper(mapper, Objects::nonNull);
    }

    public static <T> Predicate<T> notBlank(Function<T, String> mapper) {
        return mapper(mapper, StringUtils::isNotBlank);
    }

    public static <T, R> Predicate<T> mapper(Function<T, R> mapper, Predicate<R> predicate) {
        return t -> predicate.test(mapper.apply(t));
    }
}
