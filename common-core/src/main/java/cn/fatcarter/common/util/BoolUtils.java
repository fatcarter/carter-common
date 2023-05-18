package cn.fatcarter.common.util;

public class BoolUtils {
    public static boolean fromInt(int value) {
        if (value != 1 && value != 0) {
            throw new IllegalArgumentException("value must be 1 or 0");
        }
        return value == 1;
    }

    public static boolean fromInt(String value) {
        return fromInt(Integer.parseInt(value));
    }

    public static boolean fromInt(char value) {
        return fromInt(String.valueOf(value));
    }
}
