package cn.fatcarter.common.util;

public class ArrayUtils {
    public static <T> boolean isEmpty(T[] ts) {
        if(ts == null) return true;
        return ts.length ==0;
    }

    public static <T> boolean isBlank(T[] ts) {
        if (isEmpty(ts)) {
            return true;
        }
        for (T t : ts) {
            if (t != null) {
                return false;
            }
        }
        return true;
    }
}
