package cn.fatcarter.common.util;

import java.io.Serializable;
import java.util.function.Function;


/**
 * 可序列化的Function
 *
 * @param <T>
 * @param <R>
 */
public interface SFunction<T, R> extends Function<T, R>, Serializable {
}
