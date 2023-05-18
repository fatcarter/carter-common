package cn.fatcarter.common.util;


public abstract class Assert {

    public static void isTrue(boolean expression, String errorMsg) {
        if (!expression) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public static void hasText(String text, String errorMsg) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public static void notNull(Object obj, String errorMsg) {
        if (obj == null) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public static void greaterThenZero(Integer i, String errorMsg) {
        if (i == null || i <= 0) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public static void greaterThenZero(Double i, String errorMsg) {
        if (i == null || i <= 0) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public static void greaterThenZero(Float i, String errorMsg) {
        if (i == null || i <= 0) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public static void greaterThenZero(Short i, String errorMsg) {
        if (i == null || i <= 0) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public static void greaterThenZero(Byte i, String errorMsg) {
        if (i == null || i <= 0) {
            throw new IllegalArgumentException(errorMsg);
        }
    }
}
