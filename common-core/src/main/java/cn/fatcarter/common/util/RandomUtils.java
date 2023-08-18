package cn.fatcarter.common.util;

import java.text.NumberFormat;

public final class RandomUtils {


    public static String randomString(int len) {
        if (len <= 11) {
            double random = Math.random();
            NumberFormat format = getFormat();
            String f = format.format(random);
            System.out.println(f);
            String substring = f.substring(2);
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

    private static NumberFormat instance;

    private static NumberFormat getFormat() {
        if (instance == null) {
            instance = NumberFormat.getInstance();
            instance.setGroupingUsed(false);
            instance.setMaximumFractionDigits(16);
            instance.setMinimumFractionDigits(0);
            instance.setMaximumIntegerDigits(20);
        }
        return instance;
    }
}
