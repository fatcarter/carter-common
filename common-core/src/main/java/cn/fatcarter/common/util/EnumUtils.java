package cn.fatcarter.common.util;

import cn.fatcarter.common.reflect.ReflectUtils;

import java.util.ArrayList;
import java.util.List;

public class EnumUtils {

    public static <T extends Enum<T>> List<T> omit(Class<T> clz, List<T> omits) {
        Assert.isFalse(clz == null || !clz.isEnum(), "clz必须是枚举类");
        Assert.notNull(omits, "omits不能为null");
        T[] items = clz.getEnumConstants();
        List<T> result = new ArrayList<>(List.of(items));
        result.removeAll(omits);
        return result;
    }

    public static <T extends Enum<T>> List<T> omit(Class<T> clz, T... omits) {
        Assert.notNull(omits, "omits不能为null");
        return omit(clz, new ArrayList<>(List.of(omits)));
    }
}
