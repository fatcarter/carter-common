package cn.fatcarter.common.util;

public final class StringUtils {
    public static String padStartBlank(String src, int size) {
        return padStart(src, size, " ");
    }

    public static String padStartZero(String src, int size) {
        return padStart(src, size, String.valueOf(0));
    }

    public static String padStart(String src, int size, String c) {
        if (size == 1) {
            return c + src;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(c);
        }
        return builder.append(src).toString();
    }

    public static String padEndBlank(String src, int size) {
        return padEnd(src, size, " ");
    }

    public static String padEndZero(String src, int size) {
        return padEnd(src, size, String.valueOf(0));
    }

    public static String padEnd(String src, int size, String c) {
        if (size == 1) {
            return src + c;
        }
        StringBuilder builder = new StringBuilder(src);
        for (int i = 0; i < size; i++) {
            builder.append(c);
        }
        return builder.toString();
    }

    public static boolean isBlank(String src) {
        if (isEmpty(src)) {
            return true;
        }
        return !containsText(src);
    }


    public static boolean isEmpty(String src) {
        return src == null || src.trim().isEmpty();
    }

    private static boolean containsText(CharSequence str) {
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "操作失败!";
        System.out.println(isBlank(str));
    }
}
