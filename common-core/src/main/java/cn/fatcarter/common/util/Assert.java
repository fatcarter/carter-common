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
}
