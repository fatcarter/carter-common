package cn.fatcarter.common.function;

import java.util.function.Predicate;

public final class Predicates {
    private Predicates() {
    }


    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }
}
