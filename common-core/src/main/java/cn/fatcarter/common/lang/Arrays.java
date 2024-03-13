package cn.fatcarter.common.lang;

import cn.fatcarter.common.util.ArrayUtils;
import cn.fatcarter.common.util.Assert;

import java.lang.reflect.Array;

public class Arrays {
    public static <T> T[] of(T... t) {
        Assert.isFalse(ArrayUtils.isBlank(t), "数组不能为空!");
        T[] o = (T[]) Array.newInstance(t[0].getClass(), t.length);
        System.arraycopy(t, 0, o, 0, t.length);
        return o;
    }

    public static <T> T[] newInstance(Class<T> clz,int size) {
        return (T[]) Array.newInstance(clz, size);
    }
}
