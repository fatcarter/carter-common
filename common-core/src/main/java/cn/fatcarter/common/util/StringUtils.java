package cn.fatcarter.common.util;

import java.util.Arrays;

public final class StringUtils {
    public static final String EMPTY = "";
    public static final String SPACE = " ";

    public static String padStartSpace(String src, int size) {
        return padStart(src, size, SPACE);
    }

    /**
     * @deprecated use padStartSpace
     */
    @Deprecated
    public static String padStartBlank(String src, int size) {
        return padStartSpace(src, size);
    }

    public static String padStartZero(String src, int size) {
        return padStart(src, size, String.valueOf(0));
    }

    public static String padStart(String src, int size, String c) {
        if (src == null) {
            return null;
        }
        if (src.length() > size) {
            return src;
        }
        return repeat(c, size - src.length()) + src;
    }

    /**
     * @deprecated use padEndSpace
     */
    @Deprecated
    public static String padEndBlank(String src, int size) {
        return padEndSpace(src, size);
    }

    public static String padEndSpace(String src, int size) {
        return padEnd(src, size, SPACE);
    }

    public static String padEndZero(String src, int size) {
        return padEnd(src, size, String.valueOf(0));
    }

    public static String padEnd(String src, int size, String c) {
        if (src == null) return null;
        if (src.length() > size) {
            return src;
        }
        return src + repeat(c, size - src.length());
    }

    public static boolean isBlank(String src) {
        if (isEmpty(src)) {
            return true;
        }
        return !containsText(src);
    }

    public static boolean isNotBlank(String src) {
        return !isBlank(src);
    }


    public static boolean isEmpty(String src) {
        return src == null || src.trim().isEmpty();
    }

    public static boolean isNotEmpty(String src) {
        return !isEmpty(src);
    }

    public static String repeat(String src, int count) {
        return repeat(src, "", count);
    }

    public static String repeat(String src, String delimiter, int count) {
        String[] sources = new String[count];
        Arrays.fill(sources, src);
        return String.join(delimiter, sources);
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

}
