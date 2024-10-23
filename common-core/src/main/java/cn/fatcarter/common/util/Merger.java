package cn.fatcarter.common.util;

import java.util.function.BinaryOperator;

public class Merger {
    public static <T> BinaryOperator<T> firstMerger() {
        return (m1, m2) -> m1;
    }

    public static <T> BinaryOperator<T> lastMerger() {
        return (m1, m2) -> m2;
    }

    public static <T> BinaryOperator<T> throwMerger() {
        return (m1, m2) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", m1));
        };
    }
}
