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

    public static <T> String join(String delimiter, T... ts) {
        if (isEmpty(ts)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ts.length; i++) {
            String value = String.valueOf(ts[i]);
            builder.append(value);
            if (i + 1 < ts.length) {
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }
}
