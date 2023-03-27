package cn.fatcarter.common.util;

public final class StringUtils {
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
