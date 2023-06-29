package cn.fatcarter.common.util;

public final class RandomUtils {
    public static String randomString(int len) {
        if (len <= 11) {
            String substring = String.valueOf(Math.random()).substring(2);
            long i = Long.parseUnsignedLong(substring);
            String res = Long.toUnsignedString(i, 36);
            if (res.length() < len) {
                return StringUtils.padEnd(res, len - res.length(), "0");
            } else if (res.length() > len) {
                return res.substring(0, len);
            }
            return res;
        } else {
            return randomString(11) + randomString(len - 11);
        }
    }
}
